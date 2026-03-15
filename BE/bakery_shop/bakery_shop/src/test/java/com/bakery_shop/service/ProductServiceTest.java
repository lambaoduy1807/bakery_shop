package com.bakery_shop.service;

import com.bakery_shop.model.dto.ProductDTO;
import com.bakery_shop.model.entity.ProductEntity;
import com.bakery_shop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private ProductEntity productEntity;
    private ProductDTO productDTO;
    private UUID productId;

    @BeforeEach
    void setUp() {
        productId = UUID.randomUUID();

        productEntity = new ProductEntity();
        productEntity.setId(productId);
        productEntity.setName("Cake");
        productEntity.setPrice(150.0);

        productDTO = new ProductDTO();
        productDTO.setId(productId);
        productDTO.setName("Cake");
        productDTO.setPrice(150.0);
    }

    @Test
    void getProducts_Success() {
        Page<ProductEntity> page = new PageImpl<>(Arrays.asList(productEntity));
        when(productRepository.findAll(any(PageRequest.class))).thenReturn(page);

        Page<ProductDTO> result = productService.getProducts(0);

        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals("Cake", result.getContent().get(0).getName());
        verify(productRepository, times(1)).findAll(any(PageRequest.class));
    }

    @Test
    void getProductsByCategories_Success() {
        Page<ProductEntity> page = new PageImpl<>(Arrays.asList(productEntity));
        when(productRepository.findByCategory(eq("Dessert"), any(Pageable.class))).thenReturn(page);

        Page<ProductDTO> result = productService.getProductsByCategories(0, "Dessert");

        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals("Cake", result.getContent().get(0).getName());
        verify(productRepository, times(1)).findByCategory(eq("Dessert"), any(Pageable.class));
    }

    @Test
    void getAll_Success() {
        when(productRepository.findAll()).thenReturn(Arrays.asList(productEntity));

        List<ProductDTO> result = productService.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Cake", result.get(0).getName());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void getById_Success() {
        when(productRepository.findById(productId)).thenReturn(Optional.of(productEntity));

        ProductDTO result = productService.getById(productId);

        assertNotNull(result);
        assertEquals(productId.toString(), result.getId());
        assertEquals("Cake", result.getName());
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void getById_NotFound() {
        UUID randomId = UUID.randomUUID();
        when(productRepository.findById(randomId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> productService.getById(randomId));

        assertEquals("Product not found with id " + randomId, exception.getMessage());
        verify(productRepository, times(1)).findById(randomId);
    }

    @Test
    void create_Success() {
        when(productRepository.save(any(ProductEntity.class))).thenReturn(productEntity);

        ProductDTO result = productService.create(productDTO);

        assertNotNull(result);
        assertEquals("Cake", result.getName());
        verify(productRepository, times(1)).save(any(ProductEntity.class));
    }

    @Test
    void update_Success() {
        ProductDTO updateRequest = new ProductDTO();
        updateRequest.setName("Updated Cake");
        updateRequest.setPrice(200.0);

        ProductEntity updatedEntity = new ProductEntity();
        updatedEntity.setId(productId);
        updatedEntity.setName("Updated Cake");
        updatedEntity.setPrice(200.0);

        when(productRepository.findById(productId)).thenReturn(Optional.of(productEntity));
        when(productRepository.save(any(ProductEntity.class))).thenReturn(updatedEntity);

        ProductDTO result = productService.update(productId, updateRequest);

        assertNotNull(result);
        assertEquals("Updated Cake", result.getName());
        assertEquals(200.0, result.getPrice());
        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).save(any(ProductEntity.class));
    }

    @Test
    void update_ProductNotFound() {
        UUID randomId = UUID.randomUUID();
        when(productRepository.findById(randomId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> productService.update(randomId, productDTO));

        assertEquals("Product not found with id " + randomId, exception.getMessage());
        verify(productRepository, times(1)).findById(randomId);
        verify(productRepository, never()).save(any(ProductEntity.class));
    }

    @Test
    void delete_Success() {
        when(productRepository.existsById(productId)).thenReturn(true);
        doNothing().when(productRepository).deleteById(productId);

        assertDoesNotThrow(() -> productService.delete(productId));

        verify(productRepository, times(1)).existsById(productId);
        verify(productRepository, times(1)).deleteById(productId);
    }

    @Test
    void delete_NotFound() {
        UUID randomId = UUID.randomUUID();
        when(productRepository.existsById(randomId)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> productService.delete(randomId));

        assertEquals("Product not found with id " + randomId, exception.getMessage());
        verify(productRepository, times(1)).existsById(randomId);
        verify(productRepository, never()).deleteById(any());
    }
}
