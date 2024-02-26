package com.assesment.orderservice.client;

import com.assesment.orderservice.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "api-gateway")
public interface ProductFeignClient {

    @GetMapping("/product/api/{id}")
    public ProductDto getProductById(@PathVariable int id);
}
