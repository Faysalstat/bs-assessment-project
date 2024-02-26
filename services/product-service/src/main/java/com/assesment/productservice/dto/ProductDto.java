package com.assesment.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProductDto {
    private int id;
    private String productCode;
    private String productName;
    private int quantity;
    private int quantitySold;
    private double costPricePerUnit;
    private double sellingPricePerUnit;
    private String productCategory;
}
