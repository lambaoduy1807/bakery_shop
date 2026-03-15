package com.bakery_shop.service;

import com.bakery_shop.model.dto.OrderDTO;
import com.bakery_shop.model.entity.OrderEntity;
import com.bakery_shop.model.entity.UserEntity;
import com.bakery_shop.repository.OrderRepository;
import com.bakery_shop.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private OrderService orderService;

    private OrderEntity orderEntity;
    private OrderDTO orderDTO;
    private UserEntity userEntity;
    private UUID userId;

    @BeforeEach
    void setUp() {
        userId = UUID.randomUUID();

        userEntity = new UserEntity();
        userEntity.setId(userId);

        orderEntity = new OrderEntity();
        orderEntity.setId(1L);
        orderEntity.setSubtotal(100.0);
        orderEntity.setTotal(120.0);
        orderEntity.setDateCreated(LocalDateTime.now());
        orderEntity.setUser(userEntity);

        orderDTO = new OrderDTO();
        orderDTO.setId(1L);
        orderDTO.setSubtotal(100.0);
        orderDTO.setTotal(120.0);
        orderDTO.setDateCreated(orderEntity.getDateCreated());
        orderDTO.setUserId(userId);
    }

    @Test
    void getAll_Success() {
        when(orderRepository.findAll()).thenReturn(Arrays.asList(orderEntity));

        List<OrderDTO> result = orderService.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(120.0, result.get(0).getTotal());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void getById_Success() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(orderEntity));

        OrderDTO result = orderService.getById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(120.0, result.getTotal());
        verify(orderRepository, times(1)).findById(1L);
    }

    @Test
    void getById_NotFound() {
        when(orderRepository.findById(2L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> orderService.getById(2L));

        assertEquals("Order not found with id 2", exception.getMessage());
        verify(orderRepository, times(1)).findById(2L);
    }

    @Test
    void create_SuccessWithUser() {
        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(orderEntity);

        OrderDTO result = orderService.create(orderDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(userRepository, times(1)).findById(userId);
        verify(orderRepository, times(1)).save(any(OrderEntity.class));
    }

    @Test
    void create_UserNotFound() {
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> orderService.create(orderDTO));

        assertEquals("User not found with id " + userId, exception.getMessage());
        verify(userRepository, times(1)).findById(userId);
        verify(orderRepository, never()).save(any(OrderEntity.class));
    }

    @Test
    void update_Success() {
        OrderDTO updateRequest = new OrderDTO();
        updateRequest.setTotal(200.0);
        updateRequest.setUserId(userId);

        OrderEntity updatedEntity = new OrderEntity();
        updatedEntity.setId(1L);
        updatedEntity.setTotal(200.0);
        updatedEntity.setUser(userEntity);

        when(orderRepository.findById(1L)).thenReturn(Optional.of(orderEntity));
        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(updatedEntity);

        OrderDTO result = orderService.update(1L, updateRequest);

        assertNotNull(result);
        assertEquals(200.0, result.getTotal());
        verify(orderRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).findById(userId);
        verify(orderRepository, times(1)).save(any(OrderEntity.class));
    }

    @Test
    void update_OrderNotFound() {
        when(orderRepository.findById(2L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> orderService.update(2L, orderDTO));

        assertEquals("Order not found with id 2", exception.getMessage());
        verify(orderRepository, times(1)).findById(2L);
        verify(orderRepository, never()).save(any(OrderEntity.class));
    }

    @Test
    void delete_Success() {
        when(orderRepository.existsById(1L)).thenReturn(true);
        doNothing().when(orderRepository).deleteById(1L);

        assertDoesNotThrow(() -> orderService.delete(1L));

        verify(orderRepository, times(1)).existsById(1L);
        verify(orderRepository, times(1)).deleteById(1L);
    }

    @Test
    void delete_NotFound() {
        when(orderRepository.existsById(2L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> orderService.delete(2L));

        assertEquals("Order not found with id 2", exception.getMessage());
        verify(orderRepository, times(1)).existsById(2L);
        verify(orderRepository, never()).deleteById(anyLong());
    }
}
