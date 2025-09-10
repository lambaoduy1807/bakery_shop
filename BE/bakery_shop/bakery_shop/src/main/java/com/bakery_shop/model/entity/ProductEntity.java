package com.bakery_shop.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {
    @Id
    String id;
    String name;
    String img;
    String description;
    double price;
}
