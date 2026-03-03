package com.bakery_shop.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cart")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double subtotal;

    @OneToOne
    @JoinColumn(name = "userID")
    private UserEntity user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItemEntity.CartItem> items;
}
