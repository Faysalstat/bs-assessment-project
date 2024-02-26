package com.assesment.productservice.serviceImp;

import com.assesment.productservice.dto.ProductDto;
import com.assesment.productservice.entity.Product;
import com.assesment.productservice.mapper.ProductMapper;
import com.assesment.productservice.repository.ProductRepository;
import com.assesment.productservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImp  implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDto> bulkCreateProduct(List<ProductDto> productDtoList) {
        List<Product> products = productDtoList.stream()
                .map(productMapper::mapProductToEntity)
                .collect(Collectors.toList());
        List<Product> savedProducts = productRepository.saveAll(products);
        return savedProducts.stream()
                .map(productMapper::mapProductToDTO)
                .collect(Collectors.toList());
    }
    @Override
    public ProductDto createProduct(ProductDto productDTO) {
        Product product = productMapper.mapProductToEntity(productDTO);
        return productMapper.mapProductToDTO(productRepository.save(product));
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        if (!productList.isEmpty()) {
            return productList.stream().map(productMapper::mapProductToDTO)
                    .collect(Collectors.toList());
        }else {
            log.error("Product Not Found");
            return null;
        }
    }

    @Override
    public ProductDto getProductById(int productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            return productMapper.mapProductToDTO(product);
        }else {
            log.error("Product Not Found");
            return null;
        }
    }

    @Override
    public ProductDto updateProduct(ProductDto productDTO) {
        Optional<Product> productOptional = productRepository.findById(productDTO.getId());
        if (productOptional.isPresent()) {
            Product product = productMapper.mapProductToEntity(productDTO);
            return productMapper.mapProductToDTO(productRepository.saveAndFlush(product));
        }
        return null;
    }

    @Override
    public boolean deleteProduct(int productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            productRepository.delete(productOptional.get());
            log.info("Product Deleted");
            return true;
        }else {
            log.error("Product Not Found by ID");
            return false;
        }

    }
}
