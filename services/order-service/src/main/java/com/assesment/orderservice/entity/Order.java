package com.assesment.orderservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "order_no")
    private String orderNo;

    @Column(name = "quantity_ordered")
    private int quantityOrdered;

    @Column(name = "price_per_unit")
    private double pricePerUnit;

    @Column(name = "quantity_delivered")
    private int quantityDelivered;

    @Column(name = "quantity_delivery_pending")
    private int quantityDeliveryPending;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "state")
    private String state;

    @Column(name = "delivery_status")
    private String deliveryStatus;

    @Column(name = "tnx_date")
    private LocalDate  tnxDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

}
