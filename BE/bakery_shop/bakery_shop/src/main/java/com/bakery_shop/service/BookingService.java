//package com.bakery_shop.service;
//
//import com.bakery_shop.model.Mapper;
//import com.bakery_shop.model.dto.BookingDTO;
//import com.bakery_shop.model.request.RequestBooking;
//import com.bakery_shop.repository.BookingRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class BookingService {
//    private final BookingRepository bookingRepository;
//    private Mapper mapper;
//    public BookingDTO book(RequestBooking requestBooking) {
//
//       return  mapper.mapBookingEntityToDTO( bookingRepository.insert(mapper.mapRequestToBookingEntity(requestBooking)));
//    }
//}
