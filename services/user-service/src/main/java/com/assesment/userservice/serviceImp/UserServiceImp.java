package com.assesment.userservice.serviceImp;

import com.assesment.userservice.dto.UserDetailsDTO;
import com.assesment.userservice.dto.UserEvent;
import com.assesment.userservice.dto.UserEventType;
import com.assesment.userservice.entity.UserDetails;
import com.assesment.userservice.exceptions.BadRequestException;
import com.assesment.userservice.exceptions.ResourceNotFoundException;
import com.assesment.userservice.mapper.DomainToDtoMapper;
import com.assesment.userservice.producer.UserEventProducer;
import com.assesment.userservice.repository.UserDetailsRepo;
import com.assesment.userservice.repositoryImp.UserRepoImp;
import com.assesment.userservice.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImp implements UserService {

    @Autowired
    private UserDetailsRepo userDetailsRepo;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private DomainToDtoMapper domainToDtoMapper;
    @Autowired
    private UserRepoImp userRepoImp;

    @Autowired
    private UserEventProducer userEventProducer;

    @Override
    @Transactional(readOnly = true)
    public List<UserDetails> getAllUsers() {
        List<UserDetails> users = new ArrayList<>();
        List<Object> data = redisTemplate.opsForList().range("allUserDetails", 0, -1); // Retrieve data from cache
        if (data != null && data.isEmpty()) {
            // If data is not found in cache, fetch it from the database
            users= userDetailsRepo.findAll();
            redisTemplate.opsForList().leftPushAll("allUserDetails", users.toArray());
            return users;
        }else{
            assert data!=null;
            return data.stream()
                    .map(this::mapToUserDetails)
                    .collect(Collectors.toList());
        }
    }

    @Override
    @CacheEvict(value = "allUserDetails", allEntries = true)
    public UserDetails create(UserDetailsDTO userDetailsDTO) throws JsonProcessingException {

        try {
            UserDetails userDetailsModel = domainToDtoMapper.userDetailsToUserDetailsDto(userDetailsDTO);
            UserDetails newUserDetails = userDetailsRepo.save(userDetailsModel);//        Creating an user event for creating
//            Creating Success Event
            UserEvent<UserDetails> userEvent = new UserEvent<UserDetails>();
            userEvent.setEventId(newUserDetails.getId());
            userEvent.setUserEventType(UserEventType.CREATED);
            userEvent.setUserDetails(newUserDetails);
            System.out.println(userEvent.toString());
            if(newUserDetails.getId() != null){
                userEventProducer.sendUserEvent(userEvent);
            }
            redisTemplate.delete("allUserDetails");
            return newUserDetails;
        }catch (Exception e){
            //            Creating Success Event
            log.error(e.getMessage());
            UserEvent<UserDetailsDTO> userEvent = new UserEvent<UserDetailsDTO>();
            userEvent.setEventId(0);
            userEvent.setUserEventType(UserEventType.OPERATION_FAILED);
            userEvent.setUserDetails(userDetailsDTO);
            userEventProducer.sendUserEvent(userEvent);
            log.error("Operation Failed. Error: {}",e.getMessage());
            throw  new BadRequestException("Please Provide correct Data to Create");
        }
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "userDetails", key = "#id")
    public UserDetails getUserDetailsById(Integer id) throws Exception {
        Optional<UserDetails> userDetails = userRepoImp.findById(id);
        if(userDetails.isEmpty()){
            throw new ResourceNotFoundException("No User found for the ID:"+id);
        }
        return userDetails.get();
    }


    @Override
    @CacheEvict(value = "userDetails", allEntries = true)
    public UserDetails updateUser(UserDetailsDTO userDetailsDto) throws JsonProcessingException {
        try {
            UserDetails userDetails = domainToDtoMapper.userDetailsToUserDetailsDto(userDetailsDto);
            if(userDetailsRepo.findById(userDetails.getId()).isEmpty()){
                throw  new BadRequestException("No User Found For ID: "+ userDetails.getId());
            }
            UserDetails updatedUserDetails = userDetailsRepo.saveAndFlush(userDetails);

            //        Creating an user event for creating
            UserEvent<UserDetails> userEvent = new UserEvent<UserDetails>();
            userEvent.setEventId(updatedUserDetails.getId());
            userEvent.setUserEventType(UserEventType.CREATED);
            userEvent.setUserDetails(updatedUserDetails);
            userEventProducer.sendUserEvent(userEvent);
            redisTemplate.delete("allUserDetails");
            return updatedUserDetails;
        }catch (Exception e){
            //            Creating Success Event
            UserEvent<UserDetailsDTO> userEvent = new UserEvent<UserDetailsDTO>();
            userEvent.setEventId( null);
            userEvent.setUserEventType(UserEventType.OPERATION_FAILED);
            userEvent.setUserDetails(userDetailsDto);
            userEventProducer.sendUserEvent(userEvent);
            log.error("Operation Failed. Error: {}",e.getMessage());
            throw  new BadRequestException("Please Provide correct Data to Create");
        }
    }

    @Override
    @CacheEvict(value = "allUserDetails", allEntries = true)
    public String deleteUser(Integer id) {
        if(userDetailsRepo.findById(id).isEmpty()){
            throw  new BadRequestException("No User Found For ID: "+ id);
        }
        userDetailsRepo.deleteById(id);
        redisTemplate.delete("allUserDetails");
        return "Successfully Deleted";
    }
    private UserDetails mapToUserDetails(Object data) {
        UserDetails userDetails = new UserDetails();
        userDetails.setId(((UserDetails) data).getId());
        userDetails.setFirstName(((UserDetails) data).getFirstName());
        userDetails.setLastName(((UserDetails) data).getLastName());
        userDetails.setEmail(((UserDetails) data).getEmail());
        userDetails.setAddress(((UserDetails) data).getAddress());
        userDetails.setContactNo(((UserDetails) data).getContactNo());
        userDetails.setId(((UserDetails) data).getId());
        return userDetails;
    }

}
