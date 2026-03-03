package com.bakery_shop.model.entity;

import jakarta.persistence.*;

public class CartItemEntity {
    @Entity
    @Table(name = "cartitem")
    public class CartItem {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private Integer quantity;
        private Double price;

        @ManyToOne
        @JoinColumn(name = "cartID")
        private CartEntity cart;

        @ManyToOne
        @JoinColumn(name = "productID")
        private ProductEntity product;
    }
}
