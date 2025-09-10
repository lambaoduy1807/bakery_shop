package com.bakery_shop.controller;

import com.bakery_shop.model.ApiResponse;
import com.bakery_shop.model.dto.ProductDTO;
import com.bakery_shop.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    ProductService productService;

    @GetMapping
    public ApiResponse getProducts(@RequestParam int page) {
        Page<ProductDTO> data = productService.getProducts(page);
        if (data.hasContent()) {
            return ApiResponse.success(data, "Lay danh sach trang " + page + " thanh cong");
        }else{
            return ApiResponse.success(data, "Lay danh sach trang " + page + " that bai");
        }

    }
}
