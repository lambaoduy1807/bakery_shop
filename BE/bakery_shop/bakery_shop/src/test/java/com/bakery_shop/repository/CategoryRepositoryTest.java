package com.bakery_shop.repository;

import com.bakery_shop.model.entity.CategoryEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest // chỉ load cấu hình MongoRepository
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("Save and find category by id")
    void testSaveAndFindById() {
        // given
        CategoryEntity category = new CategoryEntity(null, "Bread", "100");

        // when
        CategoryEntity saved = categoryRepository.save(category);

        // then
        Optional<CategoryEntity> found = categoryRepository.findById(saved.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Bread");
        assertThat(found.get().getNum_in_stock()).isEqualTo("100");
    }

    @Test
    @DisplayName("Find all categories")
    void testFindAll() {
        // given
        categoryRepository.deleteAll(); // clear collection
        categoryRepository.save(new CategoryEntity(null, "Cake", "50"));
        categoryRepository.save(new CategoryEntity(null, "Drink", "30"));

        // when
        List<CategoryEntity> categories = categoryRepository.findAll();

        // then
        assertThat(categories).hasSize(2);
        assertThat(categories)
                .extracting(CategoryEntity::getName)
                .containsExactlyInAnyOrder("Cake", "Drink");
    }

    @Test
    @DisplayName("Delete category")
    void testDelete() {
        // given
        CategoryEntity category = categoryRepository.save(new CategoryEntity(null, "Ice Cream", "20"));

        // when
        categoryRepository.deleteById(category.getId());

        // then
        Optional<CategoryEntity> deleted = categoryRepository.findById(category.getId());
        assertThat(deleted).isEmpty();
    }
}
