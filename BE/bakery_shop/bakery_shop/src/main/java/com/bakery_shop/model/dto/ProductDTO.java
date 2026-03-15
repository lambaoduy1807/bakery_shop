package com.bakery_shop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Reference;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
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
    private String category;
}
