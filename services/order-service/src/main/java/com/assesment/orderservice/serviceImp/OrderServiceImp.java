package com.assesment.orderservice.serviceImp;

import com.assesment.orderservice.client.ProductFeignClient;
import com.assesment.orderservice.dto.OrderDto;
import com.assesment.orderservice.dto.ProductDto;
import com.assesment.orderservice.entity.Order;
import com.assesment.orderservice.mapper.OrderMapper;
import com.assesment.orderservice.repository.OrderRepository;
import com.assesment.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImp implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductFeignClient productFeignClient;
    @Override
    public List<OrderDto> createOrder(List<OrderDto> orderDtoList) {
        List<Order> orders = orderDtoList.stream()
                .map(orderMapper::mapOrderToEntity)
                .collect(Collectors.toList());
        List<Order> savedOrders = orderRepository.saveAll(orders);
        return savedOrders.stream()
                .map(orderMapper::mapOrderToDTO)
                .collect(Collectors.toList());
    }
    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orderList = orderRepository.findAll();
        if (!orderList.isEmpty()) {
            return orderList.stream().map(orderMapper::mapOrderToDTO)
                    .collect(Collectors.toList());
        }else {
            log.error("Product Not Found");
            return null;
        }
    }
    @Override
    public OrderDto getOrderById(int orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            ProductDto productRes =  productFeignClient.getProductById(order.getProduct().getId());
            OrderDto orderDto = orderMapper.mapOrderToDTO(order);
            orderDto.setProductDto(productRes);
            return orderDto;
        }else {
            log.error("Product Not Found");
            return null;
        }
    }

    @Override
    public OrderDto updateOrder(int orderId, OrderDto orderDTO) {
        Optional<Order> orderOptional = orderRepository.findById(orderDTO.getId());
        if (orderOptional.isPresent()) {
            Order order = orderMapper.mapOrderToEntity(orderDTO);
            return orderMapper.mapOrderToDTO(orderRepository.saveAndFlush(order));
        }
        return null;
    }

    @Override
    public boolean deleteOrder(int orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            orderRepository.delete(orderOptional.get());
            log.info("Order Deleted");
            return true;
        }else {
            log.error("Order Not Found by ID");
            return false;
        }
    }

}
