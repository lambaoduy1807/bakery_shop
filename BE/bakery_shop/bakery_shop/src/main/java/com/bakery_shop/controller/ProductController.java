package com.bakery_shop.controller;

import com.bakery_shop.model.ApiResponse;
import com.bakery_shop.model.ProductDTO;
import com.bakery_shop.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    ProductService productService;
@GetMapping
    public ApiResponse getProducts(@RequestParam int page) {
    List<ProductDTO> data=productService.getProducts(page);
      return ApiResponse.success(data,"Lay danh sach trang "+page+" thanh cong");
}
}
