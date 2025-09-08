package com.bakery_shop.service;

import com.bakery_shop.model.ProductDTO;
import com.bakery_shop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
ProductRepository productRepository;

//    public ProductDTO getProduct(int id) {
//        ProductDTO product = productRepository.getProduct(id);
//        return product;
//    }

    public List<ProductDTO> getProducts(int page) {
        return productRepository.getProducts( page);
    }
}
