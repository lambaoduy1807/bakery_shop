package com.bakery_shop.repository;

import com.bakery_shop.model.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository  extends JpaRepository<CartEntity, Long> {
}
