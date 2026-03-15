package com.bakery_shop.controller;

import com.bakery_shop.exception.ResponseCode;
import com.bakery_shop.model.ApiResponse;
import com.bakery_shop.model.dto.OrderDTO;
import com.bakery_shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // GET all
    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderDTO>>> getAll() {
        List<OrderDTO> res = orderService.getAll();
        if (res.isEmpty()) {
            return ResponseEntity.ok(
                    ApiResponse.error(
                            ResponseCode.NOT_FOUND.statusCode(),
                            ResponseCode.NOT_FOUND.errorCode(),
                            "No orders found"
                    )
            );
        }
        return ResponseEntity.ok(ApiResponse.success(res, ResponseCode.SUCCESS.message()));
    }

    // GET by id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderDTO>> getById(@PathVariable Long id) {
        try {
            OrderDTO dto = orderService.getById(id);
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
    public ResponseEntity<ApiResponse<OrderDTO>> create(@RequestBody OrderDTO dto) {
        try {
            OrderDTO created = orderService.create(dto);
            return ResponseEntity.ok(ApiResponse.success(created, "Created order successfully"));
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
    public ResponseEntity<ApiResponse<OrderDTO>> update(@PathVariable Long id, @RequestBody OrderDTO dto) {
        try {
            OrderDTO updated = orderService.update(id, dto);
            return ResponseEntity.ok(ApiResponse.success(updated, "Updated order successfully"));
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
            orderService.delete(id);
            return ResponseEntity.ok(ApiResponse.success(null, "Deleted order successfully"));
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

