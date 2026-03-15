package com.bakery_shop.controller;

import com.bakery_shop.exception.ResponseCode;
import com.bakery_shop.model.ApiResponse;
import com.bakery_shop.model.dto.ProductDTO;
import com.bakery_shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // GET all (không phân trang)
    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductDTO>>> getAll() {
        List<ProductDTO> res = productService.getAll();
        if (res.isEmpty()) {
            return ResponseEntity.ok(
                    ApiResponse.error(
                            ResponseCode.NOT_FOUND.statusCode(),
                            ResponseCode.NOT_FOUND.errorCode(),
                            "No products found"
                    )
            );
        }
        return ResponseEntity.ok(ApiResponse.success(res, ResponseCode.SUCCESS.message()));
    }

    // GET by id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDTO>> getById(@PathVariable UUID id) {
        try {
            ProductDTO dto = productService.getById(id);
            return ResponseEntity.ok(ApiResponse.success(dto, ResponseCode.SUCCESS.message()));
        } catch (RuntimeException e) {
            return ResponseEntity.ok(
                    ApiResponse.error(
                            ResponseCode.NOT_FOUND.statusCode(),
                            ResponseCode.NOT_FOUND.errorCode(),
                            e.getMessage()
                    )
            );
        }
    }

    // GET page
    @GetMapping("/page")
    public ResponseEntity<ApiResponse<Page<ProductDTO>>> getPage(@RequestParam int page) {
        Page<ProductDTO> data = productService.getProducts(page);
        if (data.hasContent()) {
            return ResponseEntity.ok(ApiResponse.success(data, "Lấy danh sách trang " + page + " thành công"));
        }
        return ResponseEntity.ok(
                ApiResponse.error(
                        ResponseCode.NOT_FOUND.statusCode(),
                        ResponseCode.NOT_FOUND.errorCode(),
                        "Không có sản phẩm ở trang " + page
                )
        );
    }

    // GET page by category
    @GetMapping("/page-by-category")
    public ResponseEntity<ApiResponse<Page<ProductDTO>>> getByCategory(
            @RequestParam int page,
            @RequestParam String category
    ) {
        Page<ProductDTO> data = productService.getProductsByCategories(page, category);
        if (data.hasContent()) {
            return ResponseEntity.ok(ApiResponse.success(data, "Lấy danh sách trang " + page + " theo category thành công"));
        }
        return ResponseEntity.ok(
                ApiResponse.error(
                        ResponseCode.NOT_FOUND.statusCode(),
                        ResponseCode.NOT_FOUND.errorCode(),
                        "Không có sản phẩm ở trang " + page + " với category " + category
                )
        );
    }

    // CREATE
    @PostMapping
    public ResponseEntity<ApiResponse<ProductDTO>> create(@RequestBody ProductDTO dto) {
        try {
            ProductDTO created = productService.create(dto);
            return ResponseEntity.ok(ApiResponse.success(created, "Created product successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.ok(
                    ApiResponse.error(
                            ResponseCode.SERVER_ERROR.statusCode(),
                            ResponseCode.SERVER_ERROR.errorCode(),
                            e.getMessage()
                    )
            );
        }
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDTO>> update(@PathVariable UUID id, @RequestBody ProductDTO dto) {
        try {
            ProductDTO updated = productService.update(id, dto);
            return ResponseEntity.ok(ApiResponse.success(updated, "Updated product successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.ok(
                    ApiResponse.error(
                            ResponseCode.NOT_FOUND.statusCode(),
                            ResponseCode.NOT_FOUND.errorCode(),
                            e.getMessage()
                    )
            );
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
        try {
            productService.delete(id);
            return ResponseEntity.ok(ApiResponse.success(null, "Deleted product successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.ok(
                    ApiResponse.error(
                            ResponseCode.NOT_FOUND.statusCode(),
                            ResponseCode.NOT_FOUND.errorCode(),
                            e.getMessage()
                    )
            );
        }
    }
}
