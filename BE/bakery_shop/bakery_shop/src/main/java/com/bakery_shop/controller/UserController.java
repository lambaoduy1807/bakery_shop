package com.bakery_shop.controller;

import com.bakery_shop.model.ApiResponse;
import com.bakery_shop.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class UserController {
    UserService userService;
    @GetMapping("login")
 public ApiResponse login(@RequestParam String username,@RequestParam String password) {
    return ApiResponse.success(userService.login(username,password),"Login successful");
 }
 @PostMapping("register")
 public ApiResponse register(@RequestParam String username,@RequestParam String email,@RequestParam String password) {
        return userService.register(username,email,password);
 }
 public ApiResponse forgotPassword() {
        return null;
 }
}
