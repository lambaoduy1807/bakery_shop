package com.bakery_shop.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ActiveProfiles("test") // Dùng profile test để kết nối Mongo memory hoặc test container
class BookingRepositoryTest {

    @Autowired
    private BookingRepository bookingRepository;

    @Test
    @DisplayName("Should save and find booking by id")
    void testSaveAndFindById() {
        // Given
        BookingEntity booking = new BookingEntity();
        booking.setName("John Doe");
        booking.setEmail("JohnDoe@gmail.com");
        booking.setPhone("1234567");
        booking.setNumPerson(2);
        booking.setBookingDate(LocalDateTime.of(2025, 12, 12, 0, 0));
        booking.setCreatedAt(LocalDateTime.now());
        // When
        BookingEntity savedBooking = bookingRepository.save(booking);

        // Then
        Optional<BookingEntity> found = bookingRepository.findById(savedBooking.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("John Doe");

    }

//    @Test
//    @DisplayName("Should delete booking successfully")
//    void testDeleteBooking() {
//        // Given
//        BookingEntity booking = new BookingEntity();
//        booking.setCustomerName("Alice");
//        booking.setBookingTime(LocalDateTime.now());
//        booking.setTotalAmount(200.0);
//        BookingEntity saved = bookingRepository.save(booking);
//
//        // When
//        bookingRepository.deleteById(saved.getId());
//
//        // Then
//        Optional<BookingEntity> result = bookingRepository.findById(saved.getId());
//        assertThat(result).isNotPresent();
//    }
}
