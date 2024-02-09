package com.assesment.userservice.service;

import com.assesment.userservice.dto.UserDetailsDTO;
import com.assesment.userservice.entity.UserDetails;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;


public interface UserService {

    List<UserDetails> getAllUsers();
    UserDetails create(UserDetailsDTO user) throws JsonProcessingException;
    UserDetails getUserDetailsById(Integer id) throws Exception;
    UserDetails updateUser(UserDetailsDTO user) throws JsonProcessingException;
    String deleteUser(Integer id);
}
