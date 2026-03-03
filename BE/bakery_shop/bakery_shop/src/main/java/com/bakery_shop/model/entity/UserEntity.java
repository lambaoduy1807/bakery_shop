package com.bakery_shop.model.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "User")
@Getter
@Setter
public class UserEntity {
    @Id
    private UUID id;
    private String name;
    private String password;
    private String phoneNumber;
    private String email;
    private String firstName;
    private String lastName;
    private String address;

    @ManyToOne
    @JoinColumn(name = "roleID")
    private RoleEntity role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private CartEntity.Cart cart;

    @OneToMany(mappedBy = "user")
    private List<OrderEntity> orders;

    @OneToMany(mappedBy = "user")
    private List<FavoriteProductsEntity> favorites;
}

