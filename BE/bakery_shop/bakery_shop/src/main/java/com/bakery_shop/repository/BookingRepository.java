package com.bakery_shop.repository;

import com.bakery_shop.model.entity.BookingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository  extends MongoRepository<BookingEntity, String> {
}
