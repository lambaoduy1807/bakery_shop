package com.bakery_shop.controller;

import com.bakery_shop.exception.ResponseCode;
import com.bakery_shop.model.dto.CategoryDTO;
import com.bakery_shop.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CategoryService categoryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("GET /category - should return list of categories")
    void testGetAllCategories() throws Exception {
        List<CategoryDTO> mockList = List.of(
                new CategoryDTO("1", "Bread", "100"),
                new CategoryDTO("2", "Cake", "50")
        );

        Mockito.when(categoryService.getAllCategories()).thenReturn(mockList);

        mockMvc.perform(get("/category"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(200))
                .andExpect(jsonPath("$.data", hasSize(2)))
                .andExpect(jsonPath("$.data[0].name").value("Bread"))
                .andExpect(jsonPath("$.data[1].name").value("Cake"));
    }

    @Test
    @DisplayName("GET /category/{id} - found")
    void testGetCategoryByIdFound() throws Exception {
        CategoryDTO mockDto = new CategoryDTO("1", "Bread", "100");

        Mockito.when(categoryService.getCategoryById("1")).thenReturn(Optional.of(mockDto));

        mockMvc.perform(get("/category/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(200))
                .andExpect(jsonPath("$.data.name").value("Bread"));
    }

    @Test
    @DisplayName("GET /category/{id} - not found")
    void testGetCategoryByIdNotFound() throws Exception {
        Mockito.when(categoryService.getCategoryById("99")).thenReturn(Optional.empty());

        mockMvc.perform(get("/category/99"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(ResponseCode.NOT_FOUND.statusCode()))
                .andExpect(jsonPath("$.data").doesNotExist());
    }

    @Test
    @DisplayName("POST /category - create new category")
    void testCreateCategory() throws Exception {
        CategoryDTO input = new CategoryDTO(null, "Drink", "30");
        CategoryDTO saved = new CategoryDTO("10", "Drink", "30");

        Mockito.when(categoryService.createCategory(any(CategoryDTO.class))).thenReturn(saved);

        mockMvc.perform(post("/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(200))
                .andExpect(jsonPath("$.data.name").value("Drink"));
    }

    @Test
    @DisplayName("PUT /category/{id} - update existing category")
    void testUpdateCategory() throws Exception {
        CategoryDTO input = new CategoryDTO(null, "Cake", "60");
        CategoryDTO updated = new CategoryDTO("2", "Cake", "60");

        Mockito.when(categoryService.updateCategory(eq("2"), any(CategoryDTO.class))).thenReturn(updated);

        mockMvc.perform(put("/category/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(200))
                .andExpect(jsonPath("$.data.name").value("Cake"))
                .andExpect(jsonPath("$.data.num_in_stock").value("60"));
    }

    @Test
    @DisplayName("DELETE /category/{id} - should delete category")
    void testDeleteCategory() throws Exception {
        Mockito.doNothing().when(categoryService).deleteCategory("1");

        mockMvc.perform(delete("/category/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(200))
                .andExpect(jsonPath("$.message").value("Deleted successfully"));
    }
}
