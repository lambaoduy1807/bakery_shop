package com.bakery_shop.model;

import com.bakery_shop.model.dto.BookingDTO;
import com.bakery_shop.model.dto.CategoryDTO;
import com.bakery_shop.model.dto.ProductDTO;
import com.bakery_shop.model.entity.BookingEntity;
import com.bakery_shop.model.entity.CategoryEntity;
import com.bakery_shop.model.entity.ProductEntity;
import com.bakery_shop.model.request.RequestBooking;
import lombok.AllArgsConstructor;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class Mapper {

    public ProductDTO mapProductEntityToDTO(ProductEntity product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getImg(),
                product.getDescription(),
                product.getPrice()
        );
    }

    public ProductEntity mapProductDTOToEntity(ProductDTO dto) {
        ProductEntity entity = new ProductEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setImg(dto.getImg());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        return entity;
    }

    public BookingEntity mapRequestToBookingEntity(RequestBooking request) {
        BookingEntity entity = new BookingEntity();
        entity.setName(request.getName());
        entity.setEmail(request.getEmail());
        entity.setPhone(request.getPhone());
        entity.setBookingDate(LocalDateTime.parse(request.getDate()));
        entity.setNumPerson(request.getNumPerson());
        return entity;
    }

    public BookingDTO mapBookingEntityToDTO(BookingEntity entity) {
        return new BookingDTO(entity.getId(),entity.getName(),entity.getPhone(),entity.getEmail(),entity.getNumPerson(),entity.getBookingDate(),entity.getCreatedAt());
    }

    // category Entity -> DTO
    public CategoryDTO toCategoryDTO(CategoryEntity entity) {
        return new CategoryDTO(entity.getId(), entity.getName(), entity.getNum_in_stock());
    }

    // category DTO -> Entity
    public CategoryEntity toCategoryEntity(CategoryDTO dto) {
        return new CategoryEntity(dto.getId(), dto.getName(), dto.getNumInStock());
    }
}
