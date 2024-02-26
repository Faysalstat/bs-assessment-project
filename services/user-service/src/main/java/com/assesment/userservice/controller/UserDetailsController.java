package com.assesment.userservice.controller;

import com.assesment.userservice.dto.UserDetailsDTO;
import com.assesment.userservice.entity.UserDetails;
import com.assesment.userservice.exceptions.BadRequestException;
import com.assesment.userservice.exceptions.ResourceNotFoundException;
import com.assesment.userservice.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/api")
@Slf4j
public class UserDetailsController {

    @Autowired
    private UserService userService;

    @GetMapping("/getall")
    public ResponseEntity<List<UserDetails>> getAll(){
        log.info("Got the get request");
        List<UserDetails> users = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.FOUND).body(users);
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<UserDetails> getById(@PathVariable() Integer id) throws Exception {
        log.info("Got the get by id request");
        UserDetails userDetails = userService.getUserDetailsById(id);
        if (userDetails == null) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(userDetails);
    }

    @PostMapping("/create")
    public ResponseEntity<UserDetails> create(@RequestBody UserDetailsDTO userDetails) throws JsonProcessingException {
        log.info("Got the request for creating User details");
        UserDetails createdPerson = userService.create(userDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPerson);
    }

    @PutMapping("/update")
    public ResponseEntity<UserDetails> update(@RequestBody UserDetailsDTO userDetails) throws JsonProcessingException {
        log.info("Got the request for Updating User details");
        if(userDetails.getId() == null){
            throw new BadRequestException("Id Not Provided");
        }
        UserDetails updatedUserDetails = userService.updateUser(userDetails);
        return ResponseEntity.ok(updatedUserDetails);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable() Integer id) throws Exception {
        log.info("Got the request for deleting User details by id:{}",id);
        String response = userService.deleteUser(id);
        return ResponseEntity.ok(response);
    }
}
