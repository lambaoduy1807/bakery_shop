package com.bakery_shop.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cartitem")
public class CartItemEntity {


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
