package com.bakery_shop.service;

import com.bakery_shop.exception.InvalidCredentialException;
import com.bakery_shop.exception.UserAlreadyExistsException;
import com.bakery_shop.exception.UserNotFoundException;
import com.bakery_shop.model.Mapper;
import com.bakery_shop.model.dto.UserDTO;
import com.bakery_shop.model.entity.RoleEntity;
import com.bakery_shop.model.entity.UserEntity;
import com.bakery_shop.model.request.LoginRequest;
import com.bakery_shop.model.request.RegisterRequest;
import com.bakery_shop.model.response.TokenResponse;
import com.bakery_shop.repository.UserRepository;
import com.bakery_shop.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class UserService {
    @Autowired
    RoleService roleService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    private static final String EMAIL_REGEX =
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    private static final String PHONE_REGEX =
            "^(0|\\+84)[0-9]{9}$";

    public TokenResponse login(LoginRequest loginRequest) {

        UserEntity user = userRepository.findByName(loginRequest.getUsername());

        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialException("Password is incorrect");
        }

        String accessToken = jwtUtil.generateToken(Mapper.toUserDTO(user));
        String refreshToken = jwtUtil.generateRefreshToken(user.getId());

        return new TokenResponse(refreshToken, accessToken, Mapper.toUserDTO(user));
    }

    public UserDTO register(RegisterRequest request) {

        if (userRepository.findByName(request.getName()) != null) {
            throw new UserAlreadyExistsException("Username is already in use");
        }

        String password = passwordEncoder.encode(request.getPassword());

        UserEntity res;
        RoleEntity role= roleService.getRoleEntity("User");
        if (request.getEmail_phoneNumber().matches(EMAIL_REGEX)) {

            String email = request.getEmail_phoneNumber();
            res = userRepository.save(new UserEntity(request.getName(), email, password,role));

        } else if (request.getEmail_phoneNumber().matches(PHONE_REGEX)) {

            try {
                Long phoneNumber = Long.parseLong(request.getEmail_phoneNumber());
                res = userRepository.save(new UserEntity(request.getName(), phoneNumber, password,role));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid phone number format");
            }

        } else {
            throw new IllegalArgumentException("Email hoặc số điện thoại không hợp lệ");
        }

        return Mapper.toUserDTO(res);
    }

    public UserDTO update(UserDTO request, UUID userId) {

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        request.setId(userId);

        UserEntity res = userRepository.save(Mapper.toUserEntity(request));

        return Mapper.toUserDTO(res);
    }

    public UserDTO delete(UUID userId) {

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setDelete(true);

        UserEntity res = userRepository.save(user);

        return Mapper.toUserDTO(res);
    }
}