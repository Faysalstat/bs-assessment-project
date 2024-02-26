package com.assesment.orderservice.mapper;

import com.assesment.orderservice.dto.ProductDto;
import com.assesment.orderservice.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDto mapProductToDTO(Product product) {
        ProductDto productDTO = new ProductDto();
        productDTO.setId(product.getId());
        productDTO.setProductCode(product.getProductCode());
        productDTO.setProductName(product.getProductName());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setQuantitySold(product.getQuantitySold());
        productDTO.setCostPricePerUnit(product.getCostPricePerUnit());
        productDTO.setSellingPricePerUnit(product.getSellingPricePerUnit());
        productDTO.setProductCategory(product.getProductCategory());
        return productDTO;
    }
    public Product mapProductToEntity(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setProductCode(productDto.getProductCode());
        product.setProductName(productDto.getProductName());
        product.setQuantity(productDto.getQuantity());
        product.setQuantitySold(productDto.getQuantitySold());
        product.setCostPricePerUnit(productDto.getCostPricePerUnit());
        product.setSellingPricePerUnit(productDto.getSellingPricePerUnit());
        product.setProductCategory(productDto.getProductCategory());
        return product;
    }

}
