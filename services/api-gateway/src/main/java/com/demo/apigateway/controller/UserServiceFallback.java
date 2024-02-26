package com.demo.apigateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userservice/fallback")
public class UserServiceFallback {
    @GetMapping
    public ResponseEntity<String> fallbackGet() {
        String fallbackMessage = "Fallback GET response for user-service";
        return ResponseEntity.status(HttpStatus.OK).body(fallbackMessage);
    }

    @PostMapping
    public ResponseEntity<String> fallbackPost() {
        String fallbackMessage = "Fallback POST response for user-service";
        return ResponseEntity.status(HttpStatus.OK).body(fallbackMessage);
    }
}
