package com.bakery_shop.service;

import com.bakery_shop.model.dto.CategoryDTO;
import com.bakery_shop.model.entity.CategoryEntity;
import com.bakery_shop.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    private CategoryEntity categoryEntity;
    private CategoryDTO categoryDTO;

    @BeforeEach
    void setUp() {
        categoryEntity = new CategoryEntity();
        categoryEntity.setId(1L);
        categoryEntity.setName("Croissant");
        categoryEntity.setQuantityInStock(50);

        categoryDTO = new CategoryDTO();
        categoryDTO.setId("1");
        categoryDTO.setName("Croissant");
        categoryDTO.setNumInStock("50");
    }

    @Test
    void getAllCategories_Success() {
        when(categoryRepository.findAll()).thenReturn(Arrays.asList(categoryEntity));

        List<CategoryDTO> result = categoryService.getAllCategories();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Croissant", result.get(0).getName());
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void getCategoryById_Success() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(categoryEntity));

        Optional<CategoryDTO> result = categoryService.getCategoryById(1L);

        assertTrue(result.isPresent());
        assertEquals("1", result.get().getId());
        assertEquals("Croissant", result.get().getName());
        verify(categoryRepository, times(1)).findById(1L);
    }

    @Test
    void getCategoryById_NotFound() {
        when(categoryRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<CategoryDTO> result = categoryService.getCategoryById(2L);

        assertFalse(result.isPresent());
        verify(categoryRepository, times(1)).findById(2L);
    }

    @Test
    void createCategory_Success() {
        when(categoryRepository.save(any(CategoryEntity.class))).thenReturn(categoryEntity);

        CategoryDTO result = categoryService.createCategory(categoryDTO);

        assertNotNull(result);
        assertEquals("Croissant", result.getName());
        verify(categoryRepository, times(1)).save(any(CategoryEntity.class));
    }

    @Test
    void updateCategory_Success() {
        CategoryDTO updateRequest = new CategoryDTO();
        updateRequest.setName("Updated Croissant");
        updateRequest.setNumInStock("100");

        CategoryEntity updatedEntity = new CategoryEntity();
        updatedEntity.setId(1L);
        updatedEntity.setName("Updated Croissant");
        updatedEntity.setQuantityInStock(100);

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(categoryEntity));
        when(categoryRepository.save(any(CategoryEntity.class))).thenReturn(updatedEntity);

        CategoryDTO result = categoryService.updateCategory(1L, updateRequest);

        assertNotNull(result);
        assertEquals("Updated Croissant", result.getName());
        assertEquals("100", result.getNumInStock());
        verify(categoryRepository, times(1)).findById(1L);
        verify(categoryRepository, times(1)).save(any(CategoryEntity.class));
    }

    @Test
    void updateCategory_SuccessButOnlyName() {
        CategoryDTO updateRequest = new CategoryDTO();
        updateRequest.setName("Updated Croissant");
        // numInStock is null

        CategoryEntity updatedEntity = new CategoryEntity();
        updatedEntity.setId(1L);
        updatedEntity.setName("Updated Croissant");
        updatedEntity.setQuantityInStock(50); // unchanged

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(categoryEntity));
        when(categoryRepository.save(any(CategoryEntity.class))).thenReturn(updatedEntity);

        CategoryDTO result = categoryService.updateCategory(1L, updateRequest);

        assertNotNull(result);
        assertEquals("Updated Croissant", result.getName());
        assertEquals("50", result.getNumInStock());
        verify(categoryRepository, times(1)).findById(1L);
        verify(categoryRepository, times(1)).save(any(CategoryEntity.class));
    }

    @Test
    void updateCategory_NotFound() {
        when(categoryRepository.findById(2L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> categoryService.updateCategory(2L, categoryDTO));

        assertEquals("Category not found with id 2", exception.getMessage());
        verify(categoryRepository, times(1)).findById(2L);
        verify(categoryRepository, never()).save(any(CategoryEntity.class));
    }

    @Test
    void deleteCategory_Success() {
        when(categoryRepository.existsById(1L)).thenReturn(true);
        doNothing().when(categoryRepository).deleteById(1L);

        assertDoesNotThrow(() -> categoryService.deleteCategory(1L));

        verify(categoryRepository, times(1)).existsById(1L);
        verify(categoryRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteCategory_NotFound() {
        when(categoryRepository.existsById(2L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> categoryService.deleteCategory(2L));

        assertEquals("Category not found with id 2", exception.getMessage());
        verify(categoryRepository, times(1)).existsById(2L);
        verify(categoryRepository, never()).deleteById(anyLong());
    }
}
