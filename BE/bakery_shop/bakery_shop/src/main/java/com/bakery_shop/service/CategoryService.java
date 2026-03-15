package com.bakery_shop.service;

import com.bakery_shop.model.Mapper;
import com.bakery_shop.model.dto.CategoryDTO;
import com.bakery_shop.model.entity.CategoryEntity;
import com.bakery_shop.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final Mapper mapper;

    // ========= READ =========
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(mapper::toCategoryDTO)
                .collect(Collectors.toList());
    }

    public Optional<CategoryDTO> getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(mapper::toCategoryDTO);
    }

    // ========= CREATE / UPDATE =========
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        CategoryEntity entity = mapper.toCategoryEntity(categoryDTO);
        CategoryEntity saved = categoryRepository.save(entity);
        return mapper.toCategoryDTO(saved);
    }

    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        CategoryEntity existing = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id " + id));

        existing.setName(categoryDTO.getName());
        if (categoryDTO.getNumInStock() != null) {
            existing.setQuantityInStock(Integer.valueOf(categoryDTO.getNumInStock()));
        }

        CategoryEntity saved = categoryRepository.save(existing);
        return mapper.toCategoryDTO(saved);
    }

    // ========= DELETE =========
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found with id " + id);
        }
        categoryRepository.deleteById(id);
    }
}

//package com.bakery_shop.service;
//
//import com.bakery_shop.model.Mapper;
//import com.bakery_shop.model.dto.CategoryDTO;
//import com.bakery_shop.model.entity.CategoryEntity;
//import com.bakery_shop.repository.CategoryRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class CategoryService {
//
//    private final CategoryRepository categoryRepository;
//    @Autowired
//    private Mapper mapper;
//    public CategoryService(CategoryRepository categoryRepository) {
//        this.categoryRepository = categoryRepository;
//    }
//
//
//
//    public List<CategoryDTO> getAllCategories() {
//        return categoryRepository.findAll()
//                .stream()
//                .map(mapper::toCategoryDTO)
//                .collect(Collectors.toList());
//    }
//
//    public Optional<CategoryDTO> getCategoryById(String id) {
//        return categoryRepository.findById(id).map(mapper::toCategoryDTO);
//    }
//
//    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
//        CategoryEntity entity = mapper.toCategoryEntity(categoryDTO);
//        return mapper.toCategoryDTO(categoryRepository.save(entity));
//    }
//
//    public CategoryDTO updateCategory(String id, CategoryDTO categoryDTO) {
//        return categoryRepository.findById(id)
//                .map(existing -> {
//                    existing.setName(categoryDTO.getName());
//                    existing.setNum_in_stock(categoryDTO.getNumInStock());
//                    return mapper.toCategoryDTO(categoryRepository.save(existing));
//                }).orElseThrow(() -> new RuntimeException("Category not found with id " + id));
//    }
//
//    public void deleteCategory(String id) {
//        categoryRepository.deleteById(id);
//    }
//}
