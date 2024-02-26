package com.assesment.productservice.controller;

import com.assesment.productservice.dto.ProductDto;
import com.assesment.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product/api")
public class ProductController {
    @Autowired
    private ProductService productService;

    // Create Operation
    @PostMapping("/bulkcreate")
    public ResponseEntity<List<ProductDto>> bulkCreateProducts(@RequestBody List<ProductDto> Products) {
        List<ProductDto> createdProducts = productService.bulkCreateProduct(Products);
        return new ResponseEntity<>(createdProducts, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto product) {
        ProductDto createdProduct = productService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    // Read Operation
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable int id) {
        ProductDto productDto = productService.getProductById(id);
        if (productDto != null) {
            return ResponseEntity.ok(productDto);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getall")
    public ResponseEntity<List<ProductDto>> getAllProduct() {
        List<ProductDto> Product = productService.getAllProducts();
        if (Product != null) {
            return new ResponseEntity<>(Product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update Operation
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto ProductDto) {
        ProductDto updatedProduct = productService.updateProduct(ProductDto);
        if (updatedProduct != null) {
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete Operation
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int ProductId) {
        boolean deleted = productService.deleteProduct(ProductId);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
