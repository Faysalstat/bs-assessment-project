package com.assesment.orderservice.controller;


import com.assesment.orderservice.dto.OrderDto;
import com.assesment.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders/api")
public class OrderController {
    @Autowired
    private OrderService orderService;

    // Create Operation
    @PostMapping
    public ResponseEntity<List<OrderDto>> createOrders(@RequestBody List<OrderDto> orders) {
        List<OrderDto> createdOrder = orderService.createOrder(orders);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    // Read Operation
    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable int id) {
        OrderDto order = orderService.getOrderById(id);
        if (order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getall")
    public ResponseEntity<List<OrderDto>> getAllProduct() {
        List<OrderDto> Product = orderService.getAllOrders();
        if (Product != null) {
            return new ResponseEntity<>(Product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // Update Operation
    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable int id,
                                                @RequestBody OrderDto orderDTO) {
        OrderDto updatedOrder = orderService.updateOrder(id, orderDTO);
        if (updatedOrder != null) {
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete Operation
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int id) {
        boolean deleted = orderService.deleteOrder(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
