package com.bakery_shop.repository;

import com.bakery_shop.model.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<ProductEntity, String>{
    Page<ProductEntity> findByCategory(String category, Pageable pageable);
}
