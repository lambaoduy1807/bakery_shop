package com.bakery_shop.controller;

import com.bakery_shop.model.ApiResponse;
import com.bakery_shop.model.dto.UserDTO;
import com.bakery_shop.model.request.LoginRequest;
import com.bakery_shop.model.request.RegisterRequest;
import com.bakery_shop.model.response.TokenResponse;
import com.bakery_shop.security.SecurityUtil;
import com.bakery_shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;


    @PostMapping("login")
    public ApiResponse login(@RequestBody LoginRequest loginRequest) {
            return ApiResponse.success(userService.login(loginRequest), "Login successful");

    }

    @PostMapping("register")
    public ApiResponse register(@RequestBody RegisterRequest registerRequest) {
        return ApiResponse.success(userService.register(registerRequest), "Register successful");
    }

    @PutMapping("update")
    public ApiResponse updateUser(@RequestBody UserDTO updateRequest) {
        UUID userId = SecurityUtil.getCurrentUserId();
        return ApiResponse.success( userService.update(updateRequest, userId), "Update user successful");
    }
    @DeleteMapping("delete")
    public ApiResponse deleteUser(@RequestBody UUID userId) {
        return ApiResponse.success(userService.delete(userId), "delete successful");
    }
}
