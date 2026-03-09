package com.bakery_shop.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "User")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String password;
    private Long phoneNumber;
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private boolean isDelete;

    @ManyToOne
    @JoinColumn(name = "roleID")
    private RoleEntity role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private CartEntity cart;

    @OneToMany(mappedBy = "user")
    private List<OrderEntity> orders;

    @OneToMany(mappedBy = "user")
    private List<FavoriteProductsEntity> favorites;

    public UserEntity(String username, String email, String password, RoleEntity role) {
    this.name=username;
    this.password=password;
    this.email=email;
    this.role=role;
    }

    public UserEntity(String username, Long phoneNumber, String password, RoleEntity role) {
        this.name=username;
        this.password=password;
        this.phoneNumber=phoneNumber;
        this.role=role;
    }
}

