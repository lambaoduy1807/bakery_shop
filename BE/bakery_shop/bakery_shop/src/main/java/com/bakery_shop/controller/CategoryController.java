package com.bakery_shop.controller;

import com.bakery_shop.exception.ResponseCode;
import com.bakery_shop.model.ApiResponse;
import com.bakery_shop.model.dto.CategoryDTO;
import com.bakery_shop.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;
@RestController
@RequestMapping("category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // GET all
    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryDTO>>> getAll() {
        List<CategoryDTO> res = categoryService.getAllCategories();
        if (res.isEmpty()) {
            return ResponseEntity.ok(
                    ApiResponse.error(ResponseCode.NOT_FOUND.statusCode(),
                            ResponseCode.NOT_FOUND.errorCode(),
                            ResponseCode.NOT_FOUND.message())
            );
        }
        return ResponseEntity.ok(ApiResponse.success(res, ResponseCode.SUCCESS.message()));
    }

    // GET by id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryDTO>> getById(@PathVariable String id) {
        return categoryService.getCategoryById(id)
                .map(dto -> ResponseEntity.ok(ApiResponse.success(dto, ResponseCode.SUCCESS.message())))
                .orElse(ResponseEntity.ok(
                        ApiResponse.error(ResponseCode.NOT_FOUND.statusCode(),
                                ResponseCode.NOT_FOUND.errorCode(),
                                ResponseCode.NOT_FOUND.message())
                ));
    }

    // CREATE
    @PostMapping
    public ResponseEntity<ApiResponse<CategoryDTO>> create(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO created = categoryService.createCategory(categoryDTO);
        return ResponseEntity.ok(ApiResponse.success(created, "Created successfully"));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryDTO>> update(@PathVariable String id, @RequestBody CategoryDTO categoryDTO) {
        try {
            CategoryDTO updated = categoryService.updateCategory(id, categoryDTO);
            return ResponseEntity.ok(ApiResponse.success(updated, "Updated successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.ok(
                    ApiResponse.error(ResponseCode.NOT_FOUND.statusCode(),
                            ResponseCode.NOT_FOUND.errorCode(),
                            ResponseCode.NOT_FOUND.message())
            );
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Deleted successfully"));
    }
}