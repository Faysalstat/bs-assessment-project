package com.assesment.productservice.service;

import com.assesment.productservice.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<ProductDto> bulkCreateProduct(List<ProductDto> productDTOList);
    ProductDto createProduct(ProductDto prodproductDTOuct);
    List<ProductDto> getAllProducts();
    ProductDto getProductById(int productId);

    ProductDto updateProduct(ProductDto productDTO);

    boolean deleteProduct(int productId);
}
