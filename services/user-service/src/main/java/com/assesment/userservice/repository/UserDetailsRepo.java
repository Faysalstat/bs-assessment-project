package com.assesment.userservice.repository;

import com.assesment.userservice.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepo extends JpaRepository<UserDetails,Long> {

    UserDetails findAllById(Long id);
}
