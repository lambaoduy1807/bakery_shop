package com.bakery_shop.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "favoritesproduct")
@Data
public class FavoriteProductsEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "userID")
        private UserEntity user;

        @ManyToOne
        @JoinColumn(name = "productID")
        private ProductEntity product;

}
