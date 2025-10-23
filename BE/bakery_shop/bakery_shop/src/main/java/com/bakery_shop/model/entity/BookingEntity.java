package com.bakery_shop.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "bookings")
public class BookingEntity {

    @Id
    private String id;

    private String name;
    private String phone;
    private String email;
    private int numPerson;


    private LocalDateTime bookingDate;


    @CreatedDate
    private LocalDateTime createdAt;
}