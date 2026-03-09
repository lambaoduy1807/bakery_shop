package com.bakery_shop.repository;

import com.bakery_shop.model.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository  extends JpaRepository<CartItemEntity, Long> {
}
