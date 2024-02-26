package com.demo.apigateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/orderservice/fallback")
public class OrderServiceFallback {
    @GetMapping
    public ResponseEntity<String> fallbackGet() {
        String fallbackMessage = "Fallback GET response for order-service";
        return ResponseEntity.status(HttpStatus.OK).body(fallbackMessage);
    }

    @PostMapping
    public ResponseEntity<String> fallbackPost() {
        String fallbackMessage = "Fallback POST response for order-service";
        return ResponseEntity.status(HttpStatus.OK).body(fallbackMessage);
    }

    @PutMapping
    public ResponseEntity<String> fallbackPut() {
        String fallbackMessage = "Fallback PUT response for order-service";
        return ResponseEntity.status(HttpStatus.OK).body(fallbackMessage);
    }

    @DeleteMapping
    public ResponseEntity<String> fallbackDelete() {
        String fallbackMessage = "Fallback DELETE response for order-service";
        return ResponseEntity.status(HttpStatus.OK).body(fallbackMessage);
    }
}
