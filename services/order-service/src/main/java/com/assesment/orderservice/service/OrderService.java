package com.assesment.orderservice.service;

import com.assesment.orderservice.dto.OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    List<OrderDto> createOrder(List<OrderDto> orders);

    OrderDto getOrderById(int orderId);
    List<OrderDto> getAllOrders();
    OrderDto updateOrder(int orderId, OrderDto orderDTO);

    boolean deleteOrder(int orderId);
}
