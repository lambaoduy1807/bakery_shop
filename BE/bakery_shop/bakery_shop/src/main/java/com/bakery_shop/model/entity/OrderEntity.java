package com.bakery_shop.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "`order`")

public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double subtotal;
    private Double shipping;
    private Double coupon;
    private Double total;
    private String paymentMethod;
    private LocalDateTime dateCreated;
    private Boolean isDone;

    @ManyToOne
    @JoinColumn(name = "userID")
    private UserEntity user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItemEntity> items;

}
