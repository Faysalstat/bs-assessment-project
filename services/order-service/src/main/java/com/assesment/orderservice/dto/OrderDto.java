package com.assesment.orderservice.dto;

import com.assesment.orderservice.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private int id;
    private String orderNo;
    private int quantityOrdered;
    private double pricePerUnit;
    private int quantityDelivered;
    private int quantityDeliveryPending;
    private double totalPrice;
    private String state;
    private String deliveryStatus;
    private LocalDate tnxDate;
    private ProductDto productDto;
}
