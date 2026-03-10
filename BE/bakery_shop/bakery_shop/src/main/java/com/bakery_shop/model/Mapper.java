package com.bakery_shop.model;

import com.bakery_shop.model.dto.CartDTO;
import com.bakery_shop.model.dto.CartItemDTO;
import com.bakery_shop.model.dto.CategoryDTO;
import com.bakery_shop.model.dto.FavoriteProductDTO;
import com.bakery_shop.model.dto.OrderDTO;
import com.bakery_shop.model.dto.OrderItemDTO;
import com.bakery_shop.model.dto.ProductDTO;
import com.bakery_shop.model.dto.RoleDTO;
import com.bakery_shop.model.dto.UserDTO;
import com.bakery_shop.model.entity.CartEntity;
import com.bakery_shop.model.entity.CartItemEntity;
import com.bakery_shop.model.entity.CategoryEntity;
import com.bakery_shop.model.entity.FavoriteProductsEntity;
import com.bakery_shop.model.entity.OrderEntity;
import com.bakery_shop.model.entity.OrderItemEntity;
import com.bakery_shop.model.entity.ProductEntity;
import com.bakery_shop.model.entity.RoleEntity;
import com.bakery_shop.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class Mapper {

    // ============= USER =============
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
                .orderIds(entity.getOrders() != null ?
                        entity.getOrders()
                                .stream()
                                .map(OrderEntity::getId)
                                .collect(Collectors.toList())
                        : null)
                .favoriteProductIds(entity.getFavorites() != null ?
                        entity.getFavorites()
                                .stream()
                                .map(FavoriteProductsEntity::getId)
                                .collect(Collectors.toList())
                        : null)
                .build();
    }

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

        return entity;
    }

    // ============= ROLE =============
    public static RoleEntity toRoleEntity(RoleDTO dto) {
        if (dto == null) return null;
        RoleEntity entity = new RoleEntity(dto.getName());
        return entity;
    }

    public static RoleDTO toRoleDTO(RoleEntity entity) {
        if (entity == null) return null;
        RoleDTO dto = new RoleDTO(entity.getId(), entity.getName());
        return dto;
    }

    // ============= CATEGORY =============
    public static CategoryDTO toCategoryDTO(CategoryEntity entity) {
        if (entity == null) return null;

        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId() != null ? entity.getId().toString() : null);
        dto.setName(entity.getName());
        dto.setNumInStock(entity.getQuantityInStock() != null ? entity.getQuantityInStock().toString() : null);
        return dto;
    }

    public static CategoryEntity toCategoryEntity(CategoryDTO dto) {
        if (dto == null) return null;

        CategoryEntity entity = new CategoryEntity();
        if (dto.getId() != null) {
            entity.setId(Long.valueOf(dto.getId()));
        }
        entity.setName(dto.getName());
        if (dto.getNumInStock() != null) {
            entity.setQuantityInStock(Integer.valueOf(dto.getNumInStock()));
        }
        return entity;
    }

    // ============= PRODUCT =============
    public static ProductDTO toProductDTO(ProductEntity entity) {
        if (entity == null) return null;

        return ProductDTO.builder()
                .id(entity.getId() != null ? entity.getId().toString() : null)
                .name(entity.getName())
                .img(entity.getImg())
                .description(entity.getDetail())
                .price(entity.getPrice() != null ? entity.getPrice() : 0.0)
                .category(entity.getCategory() != null ? toCategoryDTO(entity.getCategory()) : null)
                .build();
    }

    public static ProductEntity toProductEntity(ProductDTO dto) {
        if (dto == null) return null;

        ProductEntity entity = new ProductEntity();
        if (dto.getId() != null) {
            entity.setId(UUID.fromString(dto.getId()));
        }
        entity.setName(dto.getName());
        entity.setImg(dto.getImg());
        entity.setDetail(dto.getDescription());
        entity.setPrice(dto.getPrice());

        return entity;
    }

    // ============= CART =============
    public static CartDTO toCartDTO(CartEntity entity) {
        if (entity == null) return null;

        CartDTO dto = new CartDTO();
        dto.setId(entity.getId());
        dto.setSubtotal(entity.getSubtotal());
        dto.setUserId(entity.getUser() != null ? entity.getUser().getId() : null);

        if (entity.getItems() != null) {
            dto.setItems(entity.getItems()
                    .stream()
                    .map(Mapper::toCartItemDTO)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    public static CartEntity toCartEntity(CartDTO dto) {
        if (dto == null) return null;

        CartEntity entity = new CartEntity();
        entity.setId(dto.getId());
        entity.setSubtotal(dto.getSubtotal());

        return entity;
    }

    public static CartItemDTO toCartItemDTO(CartItemEntity entity) {
        if (entity == null) return null;

        CartItemDTO dto = new CartItemDTO();
        dto.setId(entity.getId());
        dto.setQuantity(entity.getQuantity());
        dto.setPrice(entity.getPrice());
        dto.setProductId(entity.getProduct() != null && entity.getProduct().getId() != null
                ? entity.getProduct().getId().toString()
                : null);
        return dto;
    }

    public static CartItemEntity toCartItemEntity(CartItemDTO dto) {
        if (dto == null) return null;

        CartItemEntity entity = new CartItemEntity();
        entity.setId(dto.getId());
        entity.setQuantity(dto.getQuantity());
        entity.setPrice(dto.getPrice());

        return entity;
    }

    // ============= ORDER =============
    public static OrderDTO toOrderDTO(OrderEntity entity) {
        if (entity == null) return null;

        OrderDTO dto = new OrderDTO();
        dto.setId(entity.getId());
        dto.setSubtotal(entity.getSubtotal());
        dto.setShipping(entity.getShipping());
        dto.setCoupon(entity.getCoupon());
        dto.setTotal(entity.getTotal());
        dto.setPaymentMethod(entity.getPaymentMethod());
        dto.setDateCreated(entity.getDateCreated());
        dto.setIsDone(entity.getIsDone());
        dto.setUserId(entity.getUser() != null ? entity.getUser().getId() : null);

        if (entity.getItems() != null) {
            dto.setItems(entity.getItems()
                    .stream()
                    .map(Mapper::toOrderItemDTO)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    public static OrderEntity toOrderEntity(OrderDTO dto) {
        if (dto == null) return null;

        OrderEntity entity = new OrderEntity();
        entity.setId(dto.getId());
        entity.setSubtotal(dto.getSubtotal());
        entity.setShipping(dto.getShipping());
        entity.setCoupon(dto.getCoupon());
        entity.setTotal(dto.getTotal());
        entity.setPaymentMethod(dto.getPaymentMethod());
        entity.setDateCreated(dto.getDateCreated());
        entity.setIsDone(dto.getIsDone());

        return entity;
    }

    public static OrderItemDTO toOrderItemDTO(OrderItemEntity entity) {
        if (entity == null) return null;

        OrderItemDTO dto = new OrderItemDTO();
        dto.setId(entity.getId());
        dto.setQuantity(entity.getQuantity());
        dto.setPrice(entity.getPrice());
        dto.setProductId(entity.getProduct() != null && entity.getProduct().getId() != null
                ? entity.getProduct().getId().toString()
                : null);
        return dto;
    }

    public static OrderItemEntity toOrderItemEntity(OrderItemDTO dto) {
        if (dto == null) return null;

        OrderItemEntity entity = new OrderItemEntity();
        entity.setId(dto.getId());
        entity.setQuantity(dto.getQuantity());
        entity.setPrice(dto.getPrice());

        return entity;
    }

    // ============= FAVORITE PRODUCT =============
    public static FavoriteProductDTO toFavoriteProductDTO(FavoriteProductsEntity entity) {
        if (entity == null) return null;

        FavoriteProductDTO dto = new FavoriteProductDTO();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUser() != null ? entity.getUser().getId() : null);
        dto.setProductId(entity.getProduct() != null && entity.getProduct().getId() != null
                ? entity.getProduct().getId().toString()
                : null);
        return dto;
    }

    public static FavoriteProductsEntity toFavoriteProductsEntity(FavoriteProductDTO dto) {
        if (dto == null) return null;

        FavoriteProductsEntity entity = new FavoriteProductsEntity();
        entity.setId(dto.getId());
        return entity;
    }
}

