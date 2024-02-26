package com.assesment.productservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "product_code")
    private String productCode;

    @Column(name = "product_name")
    private String productName;


    @Column(name = "quantity")
    private int quantity;

    @Column(name = "quantity_sold")
    private int quantitySold;

    @Column(name = "cost_price_per_unit")
    private double costPricePerUnit;

    @Column(name = "selling_price_per_unit")
    private double sellingPricePerUnit;


    @Column(name = "product_category")
    private String productCategory;


    // Constructors, getters, and setters
}
