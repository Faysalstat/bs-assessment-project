package com.assesment.userservice.mapper;

import com.assesment.userservice.dto.UserDetailsDTO;
import com.assesment.userservice.entity.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class DomainToDtoMapper {

    public UserDetails userDetailsToUserDetailsDto(UserDetailsDTO userDetailsDTO){
        UserDetails userDetails = new UserDetails();
        userDetails.setId(userDetailsDTO.getId());
        userDetails.setFirstName(userDetailsDTO.getFirstName());
        userDetails.setLastName(userDetailsDTO.getLastName());
        userDetails.setEmail(userDetailsDTO.getEmail());
        userDetails.setAddress(userDetailsDTO.getAddress());
        userDetails.setContactNo(userDetailsDTO.getContactNo());
        return userDetails;
    }
}
