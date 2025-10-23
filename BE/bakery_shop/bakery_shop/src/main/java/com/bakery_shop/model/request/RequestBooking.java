package com.bakery_shop.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestBooking {
private String name;
private String phone;
private String email;
private int numPerson;
private String Date;

}
