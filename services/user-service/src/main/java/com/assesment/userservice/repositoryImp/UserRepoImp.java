package com.assesment.userservice.repositoryImp;

import com.assesment.userservice.entity.UserDetails;
import com.assesment.userservice.repository.UserDetailsRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Slf4j
public class UserRepoImp{
    @Autowired
    private UserDetailsRepo userDetailsRepo;

    public Optional<UserDetails> findById(Integer id){
        log.info("Fetching From Database");
        return userDetailsRepo.findById(id);
    }
}
