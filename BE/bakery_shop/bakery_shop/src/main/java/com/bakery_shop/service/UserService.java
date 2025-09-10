package com.bakery_shop.service;

import com.bakery_shop.model.ApiResponse;
import com.bakery_shop.model.entity.UserEntity;
import com.bakery_shop.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service

public class UserService {
    UserRepository userRepository;
    public boolean login(String username, String password) {
        return true;
    }
    public ApiResponse register(String username, String email, String password) {
        UserEntity user = userRepository.save(new UserEntity(null,username,email,password));
        if(user == null) {
            return ApiResponse.error(400, null, "Dang ky that bai");
        }else{
        return ApiResponse.success(user,"Dang ky thanh cong");
        }
    }
}
