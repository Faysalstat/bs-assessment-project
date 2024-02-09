package com.assesment.userservice.serviceImp;

import com.assesment.userservice.dto.UserDetailsDTO;
import com.assesment.userservice.dto.UserEvent;
import com.assesment.userservice.dto.UserEventType;
import com.assesment.userservice.entity.UserDetails;
import com.assesment.userservice.mapper.DomainToDtoMapper;
import com.assesment.userservice.producer.UserEventProducer;
import com.assesment.userservice.repository.UserDetailsRepo;
import com.assesment.userservice.repositoryImp.UserRepoImp;
import com.assesment.userservice.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImp implements UserService {

    @Autowired
    private UserDetailsRepo userDetailsRepo;
    @Autowired
    private DomainToDtoMapper domainToDtoMapper;
    @Autowired
    private UserRepoImp userRepoImp;

    @Autowired
    private UserEventProducer userEventProducer;

    @Override
    @Transactional(readOnly = true)
    public List<UserDetails> getAllUsers() {
        List<UserDetails> users = userDetailsRepo.findAll();
        return users;
    }

    @Override
    public UserDetails create(UserDetailsDTO userDetailsDTO) throws JsonProcessingException {
        UserDetails userDetailsModel = domainToDtoMapper.userDetailsToUserDetailsDto(userDetailsDTO);
        UserDetails newUserDetails = userDetailsRepo.save(userDetailsModel);

//        Creating an user event for creating
        UserEvent userEvent = new UserEvent();
        userEvent.setEventId(newUserDetails.getId());
        userEvent.setUserEventType(UserEventType.CREATED);
        userEvent.setUserDetails(newUserDetails);
        userEventProducer.sendUserEvent(userEvent);
        return newUserDetails;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "userDetails", key = "#id")
    public UserDetails getUserDetailsById(Long id) throws Exception {
        Optional<UserDetails> userDetails = userRepoImp.findById(id);
        return userDetails.orElse(null);
    }


    @Override
    public UserDetails updateUser(UserDetailsDTO userDetailsDto) {
        UserDetails userDetails = domainToDtoMapper.userDetailsToUserDetailsDto(userDetailsDto);
        return userDetailsRepo.saveAndFlush(userDetails);
    }

    @Override
    public String deleteUser(Long id) {
        userDetailsRepo.deleteById(id);
        return "Successfully Deleted";
    }
}
