package com.bakery_shop.controller;

import com.bakery_shop.exception.ResponseCode;
import com.bakery_shop.model.ApiResponse;
import com.bakery_shop.model.dto.CartDTO;
import com.bakery_shop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    // GET all
    @GetMapping()
    public ResponseEntity<ApiResponse<List<CartDTO>>> getAll() {
        List<CartDTO> res = cartService.getAll();
        if (res.isEmpty()) {
            return ResponseEntity.ok(
                    ApiResponse.error(
                            ResponseCode.NOT_FOUND.statusCode(),
                            ResponseCode.NOT_FOUND.errorCode(),
                            "No carts found"
                    )
            );
        }
        return ResponseEntity.ok(ApiResponse.success(res, ResponseCode.SUCCESS.message()));
    }

    // GET by id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CartDTO>> getById(@PathVariable Long id) {
        try {
            CartDTO dto = cartService.getById(id);
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

    // CREATE
    @PostMapping
    public ResponseEntity<ApiResponse<CartDTO>> create(@RequestBody CartDTO dto) {
        try {
            CartDTO created = cartService.create(dto);
            return ResponseEntity.ok(ApiResponse.success(created, "Created cart successfully"));
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
    public ResponseEntity<ApiResponse<CartDTO>> update(@PathVariable Long id, @RequestBody CartDTO dto) {
        try {
            CartDTO updated = cartService.update(id, dto);
            return ResponseEntity.ok(ApiResponse.success(updated, "Updated cart successfully"));
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
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        try {
            cartService.delete(id);
            return ResponseEntity.ok(ApiResponse.success(null, "Deleted cart successfully"));
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

