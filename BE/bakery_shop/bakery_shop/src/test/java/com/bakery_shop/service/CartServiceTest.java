package com.bakery_shop.service;

import com.bakery_shop.model.dto.CartDTO;
import com.bakery_shop.model.entity.CartEntity;
import com.bakery_shop.model.entity.UserEntity;
import com.bakery_shop.repository.CartRepository;
import com.bakery_shop.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CartService cartService;

    private CartEntity cartEntity;
    private CartDTO cartDTO;
    private UserEntity userEntity;
    private UUID userId;

    @BeforeEach
    void setUp() {
        userId = UUID.randomUUID();
        
        userEntity = new UserEntity();
        userEntity.setId(userId);

        cartEntity = new CartEntity();
        cartEntity.setId(1L);
        cartEntity.setSubtotal(100.0);
        cartEntity.setUser(userEntity);

        cartDTO = new CartDTO();
        cartDTO.setId(1L);
        cartDTO.setSubtotal(100.0);
        cartDTO.setUserId(userId);
    }

    @Test
    void getAll_Success() {
        when(cartRepository.findAll()).thenReturn(Arrays.asList(cartEntity));

        List<CartDTO> result = cartService.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(100.0, result.get(0).getSubtotal());
        verify(cartRepository, times(1)).findAll();
    }

    @Test
    void getById_Success() {
        when(cartRepository.findById(1L)).thenReturn(Optional.of(cartEntity));

        CartDTO result = cartService.getById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(100.0, result.getSubtotal());
        verify(cartRepository, times(1)).findById(1L);
    }

    @Test
    void getById_NotFound() {
        when(cartRepository.findById(2L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> cartService.getById(2L));

        assertEquals("Cart not found with id 2", exception.getMessage());
        verify(cartRepository, times(1)).findById(2L);
    }

    @Test
    void create_SuccessWithUser() {
        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));
        when(cartRepository.save(any(CartEntity.class))).thenReturn(cartEntity);

        CartDTO result = cartService.create(cartDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(userRepository, times(1)).findById(userId);
        verify(cartRepository, times(1)).save(any(CartEntity.class));
    }

    @Test
    void create_UserNotFound() {
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> cartService.create(cartDTO));

        assertEquals("User not found with id " + userId, exception.getMessage());
        verify(userRepository, times(1)).findById(userId);
        verify(cartRepository, never()).save(any(CartEntity.class));
    }

    @Test
    void update_Success() {
        CartDTO updateRequest = new CartDTO();
        updateRequest.setSubtotal(200.0);
        updateRequest.setUserId(userId);

        CartEntity updatedEntity = new CartEntity();
        updatedEntity.setId(1L);
        updatedEntity.setSubtotal(200.0);
        updatedEntity.setUser(userEntity);

        when(cartRepository.findById(1L)).thenReturn(Optional.of(cartEntity));
        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));
        when(cartRepository.save(any(CartEntity.class))).thenReturn(updatedEntity);

        CartDTO result = cartService.update(1L, updateRequest);

        assertNotNull(result);
        assertEquals(200.0, result.getSubtotal());
        verify(cartRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).findById(userId);
        verify(cartRepository, times(1)).save(any(CartEntity.class));
    }

    @Test
    void update_CartNotFound() {
        when(cartRepository.findById(2L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> cartService.update(2L, cartDTO));

        assertEquals("Cart not found with id 2", exception.getMessage());
        verify(cartRepository, times(1)).findById(2L);
        verify(cartRepository, never()).save(any(CartEntity.class));
    }

    @Test
    void delete_Success() {
        when(cartRepository.existsById(1L)).thenReturn(true);
        doNothing().when(cartRepository).deleteById(1L);

        assertDoesNotThrow(() -> cartService.delete(1L));

        verify(cartRepository, times(1)).existsById(1L);
        verify(cartRepository, times(1)).deleteById(1L);
    }

    @Test
    void delete_NotFound() {
        when(cartRepository.existsById(2L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> cartService.delete(2L));

        assertEquals("Cart not found with id 2", exception.getMessage());
        verify(cartRepository, times(1)).existsById(2L);
        verify(cartRepository, never()).deleteById(anyLong());
    }
}
