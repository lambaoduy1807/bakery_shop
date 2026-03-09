package com.bakery_shop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private UUID id;

    private String name;

    private String email;

    private Long phoneNumber;

    private String firstName;

    private String lastName;

    private String address;

    private String roleName;        // lấy từ role.getName()

    private List<Long> orderIds;    // từ list orders

    private List<Long> favoriteProductIds; // list favorite products


}