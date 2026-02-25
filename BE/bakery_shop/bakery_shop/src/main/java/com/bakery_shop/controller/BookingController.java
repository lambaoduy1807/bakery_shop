package com.bakery_shop.controller;

import com.bakery_shop.model.ApiResponse;
import com.bakery_shop.model.request.RequestBooking;
import com.bakery_shop.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
@AllArgsConstructor
public class BookingController {
    private final BookingService bookingService;
    @PostMapping("book")
    public ApiResponse book(@RequestBody RequestBooking requestBooking) {
        if(bookingService.book(requestBooking)!=null) {
            return ApiResponse.success(requestBooking, "Booking success");
        }else{
        return ApiResponse.error(500,"","Booking fail");}
    }
}
