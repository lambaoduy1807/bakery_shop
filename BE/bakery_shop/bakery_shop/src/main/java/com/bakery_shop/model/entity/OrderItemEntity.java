package com.bakery_shop.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "orderitem")
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "productID")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "orderID")
    private OrderEntiry order;

}
