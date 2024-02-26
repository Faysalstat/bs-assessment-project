package com.assesment.orderservice.mapper;

import com.assesment.orderservice.dto.OrderDto;
import com.assesment.orderservice.entity.Order;

public class OrderEntityDtoMapper {
    public OrderDto mapOrderEntityToDTO(Order order) {
        OrderDto orderDTO = new OrderDto();
        orderDTO.setId(order.getId());
        orderDTO.setOrderNo(order.getOrderNo());
        // Map other fields as needed
        return orderDTO;
    }

    public List<OrderDTO> mapOrdersToDTOs(List<Order> orders) {
        List<OrderDTO> orderDTOs = new ArrayList<>();
        for (Order order : orders) {
            orderDTOs.add(mapOrderToDTO(order));
        }
        return orderDTOs;
    }
}
