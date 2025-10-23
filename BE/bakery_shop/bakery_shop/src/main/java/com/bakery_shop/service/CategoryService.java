package com.bakery_shop.service;

import com.bakery_shop.model.Mapper;
import com.bakery_shop.model.dto.CategoryDTO;
import com.bakery_shop.model.entity.CategoryEntity;
import com.bakery_shop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    @Autowired
    private Mapper mapper;
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }



    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(mapper::toCategoryDTO)
                .collect(Collectors.toList());
    }

    public Optional<CategoryDTO> getCategoryById(String id) {
        return categoryRepository.findById(id).map(mapper::toCategoryDTO);
    }

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        CategoryEntity entity = mapper.toCategoryEntity(categoryDTO);
        return mapper.toCategoryDTO(categoryRepository.save(entity));
    }

    public CategoryDTO updateCategory(String id, CategoryDTO categoryDTO) {
        return categoryRepository.findById(id)
                .map(existing -> {
                    existing.setName(categoryDTO.getName());
                    existing.setNum_in_stock(categoryDTO.getNumInStock());
                    return mapper.toCategoryDTO(categoryRepository.save(existing));
                }).orElseThrow(() -> new RuntimeException("Category not found with id " + id));
    }

    public void deleteCategory(String id) {
        categoryRepository.deleteById(id);
    }
}
