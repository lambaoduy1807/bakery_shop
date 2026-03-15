package com.bakery_shop.service;

import com.bakery_shop.model.Mapper;
import com.bakery_shop.model.dto.CartDTO;
import com.bakery_shop.model.entity.CartEntity;
import com.bakery_shop.model.entity.UserEntity;
import com.bakery_shop.repository.CartRepository;
import com.bakery_shop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final Mapper mapper;

    // ========= READ =========
    public List<CartDTO> getAll() {
        return cartRepository.findAll()
                .stream()
                .map(mapper::toCartDTO)
                .collect(Collectors.toList());
    }

    public CartDTO getById(Long id) {
        CartEntity entity = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart not found with id " + id));
        return mapper.toCartDTO(entity);
    }

    // ========= CREATE / UPDATE =========
    public CartDTO create(CartDTO dto) {
        CartEntity entity = mapper.toCartEntity(dto);

        if (dto.getUserId() != null) {
            UserEntity user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with id " + dto.getUserId()));
            entity.setUser(user);
        }

        CartEntity saved = cartRepository.save(entity);
        return mapper.toCartDTO(saved);
    }

    public CartDTO update(Long id, CartDTO dto) {
        CartEntity existing = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart not found with id " + id));

        existing.setSubtotal(dto.getSubtotal());

        if (dto.getUserId() != null) {
            UserEntity user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with id " + dto.getUserId()));
            existing.setUser(user);
        }

        CartEntity saved = cartRepository.save(existing);
        return mapper.toCartDTO(saved);
    }

    // ========= DELETE =========
    public void delete(Long id) {
        if (!cartRepository.existsById(id)) {
            throw new RuntimeException("Cart not found with id " + id);
        }
        cartRepository.deleteById(id);
    }
}

