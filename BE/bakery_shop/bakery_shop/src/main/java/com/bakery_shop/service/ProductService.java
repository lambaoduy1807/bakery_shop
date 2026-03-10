//package com.bakery_shop.service;
//
//import com.bakery_shop.model.Mapper;
//import com.bakery_shop.model.dto.ProductDTO;
//import com.bakery_shop.model.entity.ProductEntity;
//import com.bakery_shop.repository.ProductRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class ProductService {
//    //    public ProductDTO getProduct(int id) {
////        ProductDTO product = productRepository.getProduct(id);
////        return product;
////    }
//    private final ProductRepository productRepository;
//    private final Mapper mapper;
//
//    public Page<ProductDTO> getProducts(int page) {
//        return productRepository.findAll(PageRequest.of(page, 9))
//                .map(mapper::mapProductEntityToDTO);
//    }
//
//    public Page<ProductDTO> getProductsByCategories(int page, String category) {
//        Pageable pageable = PageRequest.of(page, 9, Sort.by("name").ascending());
//        Page<ProductEntity> entities = productRepository.findByCategory(category, pageable);
//
//        // map từng entity -> DTO
//        return entities.map(mapper::mapProductEntityToDTO);
//    }
//}
