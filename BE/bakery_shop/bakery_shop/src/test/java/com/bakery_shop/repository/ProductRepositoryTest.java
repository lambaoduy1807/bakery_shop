package com.bakery_shop.repository;

import com.bakery_shop.model.entity.ProductEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void cleanDatabase() {
        productRepository.deleteAll();
    }

    @Test
    void testSaveAndFindById() {
        // given
        ProductEntity product = new ProductEntity();
        product.setName("Bánh mì ngọt");
        product.setDescription("Bánh mì mềm, nhân ngọt");
        product.setPrice(15000);

        // when: lưu xuống DB
        ProductEntity savedProduct = productRepository.save(product);

        // then: lấy lại bằng id
        Optional<ProductEntity> found = productRepository.findById(savedProduct.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Bánh mì ngọt");
        assertThat(found.get().getPrice()).isEqualByComparingTo(15000.0);
    }

    @Test
    void testFindAll() {
        // given
        ProductEntity product1 = new ProductEntity();
        product1.setName("Bánh mì thịt");
        product1.setPrice(20000);

        ProductEntity product2 = new ProductEntity();
        product2.setName("Bánh kem");
        product2.setPrice(120000);

        productRepository.save(product1);
        productRepository.save(product2);

        // when
        var allProducts = productRepository.findAll();

        // then
        assertThat(allProducts).hasSize(2)
                .extracting(ProductEntity::getName)
                .contains("Bánh mì thịt", "Bánh kem");
    }
}
