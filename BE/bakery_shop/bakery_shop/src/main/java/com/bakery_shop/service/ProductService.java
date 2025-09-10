package com.bakery_shop.service;

import com.bakery_shop.model.dto.ProductDTO;
import com.bakery_shop.model.entity.ProductEntity;
import com.bakery_shop.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
ProductRepository productRepository;

//    public ProductDTO getProduct(int id) {
//        ProductDTO product = productRepository.getProduct(id);
//        return product;
//    }

    public Page<ProductDTO> getProducts(int page) {
        return productRepository.findAll(PageRequest.of(page, 9)).map(product->
                new ProductDTO(product.getId(),product.getName(),product.getImg(),product.getDescription(),product.getPrice()));
    }
}
