package com.bakery_shop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Long id;

    private Double subtotal;
    private Double shipping;
    private Double coupon;
    private Double total;

    private String paymentMethod;
    private LocalDateTime dateCreated;
    private Boolean isDone;

    private UUID userId;

    private List<OrderItemDTO> items;
}

