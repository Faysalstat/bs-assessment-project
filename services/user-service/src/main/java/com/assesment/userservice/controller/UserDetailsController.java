package com.assesment.userservice.controller;

import com.assesment.userservice.dto.UserDetailsDTO;
import com.assesment.userservice.entity.UserDetails;
import com.assesment.userservice.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
@Slf4j
public class UserDetailsController {

    @Autowired
    private UserService userService;



    @GetMapping("/getall")

    public ResponseEntity<List<UserDetails>> getAll(){
        log.info("Got the get request");
        List<UserDetails> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<UserDetails> getById(@PathVariable() Long id) throws Exception {
        log.info("Got the get by id request");
        UserDetails userDetails = userService.getUserDetailsById(id);
        return ResponseEntity.ok(userDetails);
    }

    @PostMapping("/create")
    public ResponseEntity<UserDetails> create(@RequestBody UserDetailsDTO userDetails) throws JsonProcessingException {
        log.info("Got the request for creating User details");
        UserDetails createdPerson = userService.create(userDetails);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdPerson);
    }

    @PostMapping("/update")
    public ResponseEntity<UserDetails> update(@RequestBody UserDetailsDTO userDetails){
        log.info("Got the request for Updating User details");
        UserDetails updatedUserDetails = userService.updateUser(userDetails);
        return ResponseEntity.ok(updatedUserDetails);
    }


    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody Long id){
        log.info("Got the request for deleting User details by id:{}",id);
        String response = userService.deleteUser(id);
        return ResponseEntity.ok(response);
    }
}
