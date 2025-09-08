package com.bakery_shop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
public class ProductEntity {
    @Id
    String id;
    String name;
    String img;
    String description;
    double price;
}
