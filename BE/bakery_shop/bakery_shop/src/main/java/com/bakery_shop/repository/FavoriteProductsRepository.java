package com.bakery_shop.repository;

import com.bakery_shop.model.entity.FavoriteProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteProductsRepository  extends JpaRepository<FavoriteProductsEntity, Long> {
}
