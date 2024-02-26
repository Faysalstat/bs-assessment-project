package com.assesment.orderservice.mapper;

import com.assesment.orderservice.dto.OrderDto;
import com.assesment.orderservice.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    @Autowired
    private ProductMapper productMapper;
    public OrderDto mapOrderToDTO(Order order) {
        OrderDto orderDTO = new OrderDto();
        orderDTO.setId(order.getId());
        orderDTO.setOrderNo(order.getOrderNo());
        orderDTO.setQuantityOrdered(order.getQuantityOrdered());
        orderDTO.setPricePerUnit(order.getPricePerUnit());
        orderDTO.setQuantityDelivered(order.getQuantityDelivered());
        orderDTO.setQuantityDeliveryPending(order.getQuantityDeliveryPending());
        orderDTO.setTotalPrice(order.getTotalPrice());
        orderDTO.setState(order.getState());
        orderDTO.setDeliveryStatus(order.getDeliveryStatus());
        orderDTO.setTnxDate(order.getTnxDate());
        orderDTO.setProductDto(productMapper.mapProductToDTO(order.getProduct()));
        return orderDTO;
    }

    public Order mapOrderToEntity(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setOrderNo(orderDto.getOrderNo());
        order.setQuantityOrdered(orderDto.getQuantityOrdered());
        order.setPricePerUnit(orderDto.getPricePerUnit());
        order.setQuantityDelivered(orderDto.getQuantityDelivered());
        order.setQuantityDeliveryPending(orderDto.getQuantityDeliveryPending());
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setState(orderDto.getState());
        order.setDeliveryStatus(orderDto.getDeliveryStatus());
        order.setTnxDate(orderDto.getTnxDate());
        order.setProduct(productMapper.mapProductToEntity(orderDto.getProductDto()));
        return order;
    }


}
