package com.bakery_shop.repository;

import com.bakery_shop.model.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository  extends JpaRepository<ProductEntity, UUID> {
    Page<ProductEntity> findByCategory(String category, Pageable pageable);
}
