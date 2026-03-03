package com.bakery_shop.model.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "role")

public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

}
