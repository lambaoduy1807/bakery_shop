package com.bakery_shop.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "products")
@Getter
@Setter
public class ProductEntity {

    @Id
    private UUID id;
    private String name;
    private String detail;
    private String img;
    private Double price;
    private Double rating;
    private LocalDateTime dateAdd;
    private LocalDateTime dateModify;
    private Integer quantityInStock;
    private Boolean isDelete;

    @ManyToOne
    @JoinColumn(name = "categoryID")
    private CategoryEntity category;

    @OneToMany(mappedBy = "product")
    private List<OrderItemEntity> orderItems;

    @OneToMany(mappedBy = "product")
    private List<CartItemEntity.CartItem> cartItems;

    @OneToMany(mappedBy = "product")
    private List<FavoriteProductsEntity> favorites;
}
