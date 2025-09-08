package com.bakery_shop.service;

import com.bakery_shop.model.ProductDTO;
import com.bakery_shop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    private ProductRepository productRepository;
    private ProductService productService;

    @BeforeEach
    void setUp() {
        // mock repository
        productRepository = Mockito.mock(ProductRepository.class);

        // inject vào service
        productService = new ProductService();
        productService.productRepository = productRepository;
    }

    @Test
    void testGetProducts() {
        // given: repository giả trả về danh sách sản phẩm
        List<ProductDTO> mockProducts = Arrays.asList(
                new ProductDTO("1", "Bánh mì", "Ngon", 10000),
                new ProductDTO("2", "Bánh kem", "Ngọt", 150000)
        );

        when(productRepository.getProducts(1)).thenReturn(mockProducts);

        // when: gọi service
        List<ProductDTO> result = productService.getProducts(1);

        // then: kiểm tra
        assertEquals(2, result.size());
        assertEquals("Bánh mì", result.get(0).getName());
        assertEquals("Bánh kem", result.get(1).getName());

        // verify repository được gọi đúng
        verify(productRepository, times(1)).getProducts(1);
    }
}
