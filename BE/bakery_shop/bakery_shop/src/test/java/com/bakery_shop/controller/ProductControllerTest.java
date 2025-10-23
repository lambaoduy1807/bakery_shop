package com.bakery_shop.controller;

import com.bakery_shop.model.ApiResponse;
import com.bakery_shop.model.dto.CategoryDTO;
import com.bakery_shop.model.dto.ProductDTO;
import com.bakery_shop.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("GET /products/getAll - should return products when page has content")
    void testGetAllProducts_Success() throws Exception {
        // given
        List<ProductDTO> products = List.of(
                new ProductDTO("1", "Bread", "img1.jpg", new CategoryDTO(), "Food", 10.5),
                new ProductDTO("2", "Cake", "img2.jpg", new CategoryDTO(), "Food", 20.0)
        );
        Page<ProductDTO> page = new PageImpl<>(products);

        Mockito.when(productService.getProducts(1)).thenReturn(page);

        // when + then
        mockMvc.perform(get("/products/getAll").param("page", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(200))
                .andExpect(jsonPath("$.data.content", hasSize(2)))
                .andExpect(jsonPath("$.data.content[0].name").value("Bread"))
                .andExpect(jsonPath("$.message", containsString("thanh cong")));
    }

    @Test
    @DisplayName("GET /products/getAll - should return error when no content")
    void testGetAllProducts_Empty() throws Exception {
        Page<ProductDTO> emptyPage = Page.empty();
        Mockito.when(productService.getProducts(2)).thenReturn(emptyPage);

        mockMvc.perform(get("/products/getAll").param("page", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(500))
                .andExpect(jsonPath("$.message", containsString("that bai")));
    }

    @Test
    @DisplayName("GET /products/getByCategories - should return products for category")
    void testGetProductsByCategories_Success() throws Exception {
        List<ProductDTO> products = List.of(
                new ProductDTO("3", "Cola", "img3.jpg", new CategoryDTO(), "Drink", 5.0)
        );
        Page<ProductDTO> page = new PageImpl<>(products);

        Mockito.when(productService.getProductsByCategories(1, "Drink")).thenReturn(page);

        mockMvc.perform(get("/products/getByCategories")
                        .param("page", "1")
                        .param("category", "Drink"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(200))
                .andExpect(jsonPath("$.data.content[0].category").value("Drink"))
                .andExpect(jsonPath("$.message", containsString("thanh cong")));
    }

    @Test
    @DisplayName("GET /products/getByCategories - should return error when no products found")
    void testGetProductsByCategories_Empty() throws Exception {
        Mockito.when(productService.getProductsByCategories(1, "Snack")).thenReturn(Page.empty());

        mockMvc.perform(get("/products/getByCategories")
                        .param("page", "1")
                        .param("category", "Snack"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(500))
                .andExpect(jsonPath("$.message", containsString("that bai")));
    }
}
