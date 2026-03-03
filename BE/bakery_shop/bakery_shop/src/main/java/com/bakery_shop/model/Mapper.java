package com.bakery_shop.model;

import com.bakery_shop.model.dto.*;
import com.bakery_shop.model.entity.*;
import com.bakery_shop.model.request.FeedbackRequest;
import com.bakery_shop.model.request.RequestBooking;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class Mapper {

    // ======================
    // UTILS
    // ======================
    private Long parseLongSafe(String s) {
        try {
            return (s == null || s.isBlank()) ? null : Long.parseLong(s);
        } catch (NumberFormatException e) {
            return null; // hoặc throw custom exception tùy yêu cầu
        }
    }
    private String parseStringSafe(Long num) {
        try {
            return (num == 0) ? null : (num+"") ;
        } catch (NumberFormatException e) {
            return null; // hoặc throw custom exception tùy yêu cầu
        }
    }
    private Integer parseIntegerSafe(String s) {
        try {
            return (s == null || s.isBlank()) ? null : Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return null; // hoặc throw custom exception tùy yêu cầu
        }
    }
    // ======================
    // PRODUCT
    // ======================
    public ProductDTO mapProductEntityToDTO(ProductEntity product) {
        return new ProductDTO(
                product.getId()+"",
                product.getName(),
                product.getImg(),
                product.getDescription(),
                product.getPrice()
        );
    }

    public ProductEntity mapProductDTOToEntity(ProductDTO dto) {
        ProductEntity entity = new ProductEntity();
        entity.setId(parseLongSafe(dto.getId()));  // DTO đã là Long → giữ nguyên
        entity.setName(dto.getName());
        entity.setImg(dto.getImg());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        return entity;
    }

    // ======================
    // BOOKING
    // ======================
    public BookingEntity mapRequestToBookingEntity(RequestBooking request) {
        BookingEntity entity = new BookingEntity();
        entity.setId(null); // <--- map String → Long
        entity.setName(request.getName());
        entity.setEmail(request.getEmail());
        entity.setPhone(request.getPhone());
        entity.setBookingDate(LocalDateTime.parse(request.getDate()));
        entity.setNumPerson(request.getNumPerson());
        return entity;
    }

    public BookingDTO mapBookingEntityToDTO(BookingEntity entity) {
        return new BookingDTO(
                entity.getId()+"",
                entity.getName(),
                entity.getPhone(),
                entity.getEmail(),
                entity.getNumPerson(),
                entity.getBookingDate(),
                entity.getCreatedAt()
        );
    }

    // ======================
    // CATEGORY
    // ======================
    public CategoryDTO toCategoryDTO(CategoryEntity entity) {
        return new CategoryDTO(
               parseStringSafe( entity.getId()),
                entity.getName(),
                entity.getNumInStock()+""
        );
    }

    public CategoryEntity toCategoryEntity(CategoryDTO dto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setId(parseLongSafe(dto.getId())); // đã là Long
        entity.setName(dto.getName());
        entity.setNumInStock(parseIntegerSafe(dto.getNumInStock()));
        return entity;
    }

    // ======================
    // FEEDBACK
    // ======================
    public FeedbackDTO toFeedbackDTO(FeedbackEntity entity) {
        FeedbackDTO dto = new FeedbackDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setMessage(entity.getMessage());
        return dto;
    }

    public FeedbackEntity toFeedbackEntity(FeedbackDTO dto) {
        FeedbackEntity entity = new FeedbackEntity();
        entity.setId(dto.getId()); // nếu DTO là String, đổi thành parseLongSafe(dto.getIdString())
        entity.setName(dto.getName());
        entity.setMessage(dto.getMessage());
        return entity;
    }
    // FeedbackRequest -> FeedbackEntity
    public FeedbackEntity toFeedbackEntity(FeedbackRequest request) {
        FeedbackEntity entity = new FeedbackEntity();
        entity.setName(request.getName());
        entity.setMessage(request.getMessage());
        return entity;
    }

}
