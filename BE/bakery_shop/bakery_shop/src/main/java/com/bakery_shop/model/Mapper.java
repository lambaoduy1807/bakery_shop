package com.bakery_shop.model;

import com.bakery_shop.model.dto.RoleDTO;
import com.bakery_shop.model.dto.UserDTO;
import com.bakery_shop.model.entity.RoleEntity;
import com.bakery_shop.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class Mapper {

    // ============= ENTITY → DTO =============
    public static UserDTO toUserDTO(UserEntity entity) {
        if (entity == null) return null;

        return UserDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .phoneNumber(entity.getPhoneNumber())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .address(entity.getAddress())
                .roleName(entity.getRole() != null ? entity.getRole().getName() : null)

                // Lấy danh sách ID Orders
                .orderIds(entity.getOrders() != null ?
                        entity.getOrders()
                                .stream()
                                .map(order -> order.getId())
                                .collect(Collectors.toList())
                        : null)

                // Lấy danh sách ID FavoriteProducts
                .favoriteProductIds(entity.getFavorites() != null ?
                        entity.getFavorites()
                                .stream()
                                .map(fav -> fav.getId())
                                .collect(Collectors.toList())
                        : null)
                .build();
    }


    // ============= DTO → ENTITY =============
    public static UserEntity toUserEntity(UserDTO dto) {
        if (dto == null) return null;

        UserEntity entity = new UserEntity();

        entity.setId(dto.getId() != null ? dto.getId() : UUID.randomUUID());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setAddress(dto.getAddress());

        // ⚠ Không set password (DTO không chứa password)
        // ⚠ Không set quan hệ role, cart, orders, favorites tại đây
        // Các quan hệ nên được set ở Service để tránh lỗi vòng lặp hoặc null

        return entity;
    }
    public static RoleEntity toRoleEntity(RoleDTO dto) {
        if (dto == null) return null;
        RoleEntity entity= new RoleEntity(dto.getName());
        return entity;
    }
    public static RoleDTO toRoleDTO(RoleEntity entity) {
        if (entity == null) return null;
        RoleDTO dto = new RoleDTO(entity.getId(),entity.getName());
        return dto;
    }
}


