package com.bakery_shop.service;

import com.bakery_shop.model.Mapper;
import com.bakery_shop.model.dto.OrderDTO;
import com.bakery_shop.model.entity.OrderEntity;
import com.bakery_shop.model.entity.UserEntity;
import com.bakery_shop.repository.OrderRepository;
import com.bakery_shop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final Mapper mapper;

    // ========= READ =========
    public List<OrderDTO> getAll() {
        return orderRepository.findAll()
                .stream()
                .map(mapper::toOrderDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO getById(Long id) {
        OrderEntity entity = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id " + id));
        return mapper.toOrderDTO(entity);
    }

    // ========= CREATE / UPDATE =========
    public OrderDTO create(OrderDTO dto) {
        OrderEntity entity = mapper.toOrderEntity(dto);

        if (dto.getUserId() != null) {
            UserEntity user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with id " + dto.getUserId()));
            entity.setUser(user);
        }

        OrderEntity saved = orderRepository.save(entity);
        return mapper.toOrderDTO(saved);
    }

    public OrderDTO update(Long id, OrderDTO dto) {
        OrderEntity existing = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id " + id));

        existing.setSubtotal(dto.getSubtotal());
        existing.setShipping(dto.getShipping());
        existing.setCoupon(dto.getCoupon());
        existing.setTotal(dto.getTotal());
        existing.setPaymentMethod(dto.getPaymentMethod());
        existing.setDateCreated(dto.getDateCreated());
        existing.setIsDone(dto.getIsDone());

        if (dto.getUserId() != null) {
            UserEntity user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with id " + dto.getUserId()));
            existing.setUser(user);
        }

        OrderEntity saved = orderRepository.save(existing);
        return mapper.toOrderDTO(saved);
    }

    // ========= DELETE =========
    public void delete(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found with id " + id);
        }
        orderRepository.deleteById(id);
    }
}

