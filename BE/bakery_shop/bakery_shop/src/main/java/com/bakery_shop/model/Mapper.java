package com.bakery_shop.model;

import com.bakery_shop.model.dto.*;
import com.bakery_shop.model.entity.*;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;
import java.util.stream.Collectors;

@org.mapstruct.Mapper(componentModel = "spring", imports = {UUID.class, Collectors.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface Mapper {

    // ============= USER =============
    @Mapping(source = "role.name", target = "roleName")
    @Mapping(target = "orderIds", expression = "java(entity.getOrders() != null ? entity.getOrders().stream().map(OrderEntity::getId).collect(Collectors.toList()) : null)")
    @Mapping(target = "favoriteProductIds", expression = "java(entity.getFavorites() != null ? entity.getFavorites().stream().map(FavoriteProductsEntity::getId).collect(Collectors.toList()) : null)")
    UserDTO toUserDTO(UserEntity entity);

    @Mapping(target = "id", expression = "java(dto.getId() != null ? dto.getId() : UUID.randomUUID())")
    UserEntity toUserEntity(UserDTO dto);

    // ============= ROLE =============
    RoleEntity toRoleEntity(RoleDTO dto);
    RoleDTO toRoleDTO(RoleEntity entity);

    // ============= CATEGORY =============
    @Mapping(source = "quantityInStock", target = "numInStock")
    CategoryDTO toCategoryDTO(CategoryEntity entity);

    @Mapping(source = "numInStock", target = "quantityInStock")
    CategoryEntity toCategoryEntity(CategoryDTO dto);

    // ============= PRODUCT =============
    @Mapping(source = "category.name", target = "category")
    ProductDTO toProductDTO(ProductEntity entity);

    @Mapping(target = "category", ignore = true)
    ProductEntity toProductEntity(ProductDTO dto);

    // ============= CART =============
    @Mapping(source = "user.id", target = "userId")
    CartDTO toCartDTO(CartEntity entity);

    CartEntity toCartEntity(CartDTO dto);

    @Mapping(source = "product.id", target = "productId")
    CartItemDTO toCartItemDTO(CartItemEntity entity);

    CartItemEntity toCartItemEntity(CartItemDTO dto);

    // ============= ORDER =============
    @Mapping(source = "user.id", target = "userId")
    OrderDTO toOrderDTO(OrderEntity entity);

    OrderEntity toOrderEntity(OrderDTO dto);

    @Mapping(source = "product.id", target = "productId")
    OrderItemDTO toOrderItemDTO(OrderItemEntity entity);

    OrderItemEntity toOrderItemEntity(OrderItemDTO dto);

    // ============= FAVORITE PRODUCT =============
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "product.id", target = "productId")
    FavoriteProductDTO toFavoriteProductDTO(FavoriteProductsEntity entity);

    FavoriteProductsEntity toFavoriteProductsEntity(FavoriteProductDTO dto);
}
