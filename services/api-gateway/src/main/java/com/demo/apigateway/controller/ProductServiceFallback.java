package com.demo.apigateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/productservice/fallback")
public class ProductServiceFallback {
    @GetMapping
    public ResponseEntity<String> fallbackGet() {
        String fallbackMessage = "Fallback GET response for product-service";
        return ResponseEntity.status(HttpStatus.OK).body(fallbackMessage);
    }

    @PostMapping
    public ResponseEntity<String> fallbackPost() {
        String fallbackMessage = "Fallback POST response for product-service";
        return ResponseEntity.status(HttpStatus.OK).body(fallbackMessage);
    }

    @PutMapping
    public ResponseEntity<String> fallbackPut() {
        String fallbackMessage = "Fallback PUT response for product-service";
        return ResponseEntity.status(HttpStatus.OK).body(fallbackMessage);
    }

    @DeleteMapping
    public ResponseEntity<String> fallbackDelete() {
        String fallbackMessage = "Fallback DELETE response for product-service";
        return ResponseEntity.status(HttpStatus.OK).body(fallbackMessage);
    }
}
