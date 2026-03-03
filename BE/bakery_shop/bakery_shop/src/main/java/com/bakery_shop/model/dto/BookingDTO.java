package com.bakery_shop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
    private String id;
    private String name;
    private String phone;
    private String email;
    private int numPerson;
    private LocalDateTime bookingDate;
    private LocalDateTime createdAt;
}
