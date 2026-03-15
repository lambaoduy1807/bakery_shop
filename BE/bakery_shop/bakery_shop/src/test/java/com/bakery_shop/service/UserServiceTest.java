package com.bakery_shop.service;

import com.bakery_shop.exception.InvalidCredentialException;
import com.bakery_shop.exception.UserAlreadyExistsException;
import com.bakery_shop.exception.UserNotFoundException;
import com.bakery_shop.model.dto.UserDTO;
import com.bakery_shop.model.entity.RoleEntity;
import com.bakery_shop.model.entity.UserEntity;
import com.bakery_shop.model.request.LoginRequest;
import com.bakery_shop.model.request.RegisterRequest;
import com.bakery_shop.model.response.TokenResponse;
import com.bakery_shop.repository.UserRepository;
import com.bakery_shop.security.jwt.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private RoleService roleService;

    @InjectMocks
    private UserService userService;

    private UserEntity userEntity;
    private RoleEntity roleEntity;

    @BeforeEach
    void setUp() {
        roleEntity = new RoleEntity();
        roleEntity.setId(1L);
        roleEntity.setName("User");

        userEntity = new UserEntity("testuser", "test@test.com", "encodedpassword", roleEntity);
        userEntity.setId(UUID.randomUUID());
    }

    @Test
    void login_Success() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("testuser");
        loginRequest.setPassword("password123");

        when(userRepository.findByName("testuser")).thenReturn(userEntity);
        when(passwordEncoder.matches("password123", "encodedpassword")).thenReturn(true);
        when(jwtUtil.generateToken(any(UserDTO.class))).thenReturn("access-token");
        when(jwtUtil.generateRefreshToken(userEntity.getId())).thenReturn("refresh-token");

        TokenResponse response = userService.login(loginRequest);

        assertNotNull(response);
        assertEquals("access-token", response.getAccessToken());
        assertEquals("refresh-token", response.getRefreshToken());
        assertEquals("testuser", response.getUser().getName());
    }

    @Test
    void login_UserNotFound() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("unknownuser");
        loginRequest.setPassword("password123");

        when(userRepository.findByName("unknownuser")).thenReturn(null);

        assertThrows(UserNotFoundException.class, () -> userService.login(loginRequest));
    }

    @Test
    void login_InvalidPassword() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("testuser");
        loginRequest.setPassword("wrongpassword");

        when(userRepository.findByName("testuser")).thenReturn(userEntity);
        when(passwordEncoder.matches("wrongpassword", "encodedpassword")).thenReturn(false);

        assertThrows(InvalidCredentialException.class, () -> userService.login(loginRequest));
    }

    @Test
    void register_SuccessEmail() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setName("newuser");
        registerRequest.setEmail_phoneNumber("newuser@test.com");
        registerRequest.setPassword("password123");

        when(userRepository.findByName("newuser")).thenReturn(null);
        when(passwordEncoder.encode("password123")).thenReturn("encoded123");
        when(roleService.getRoleEntity("User")).thenReturn(roleEntity);

        UserEntity savedUser = new UserEntity("newuser", "newuser@test.com", "encoded123", roleEntity);
        savedUser.setId(UUID.randomUUID());
        when(userRepository.save(any(UserEntity.class))).thenReturn(savedUser);

        UserDTO response = userService.register(registerRequest);

        assertNotNull(response);
        assertEquals("newuser", response.getName());
        assertEquals("newuser@test.com", response.getEmail());
    }

    @Test
    void register_SuccessPhone() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setName("phoneuser");
        registerRequest.setEmail_phoneNumber("0987654321");
        registerRequest.setPassword("password123");

        when(userRepository.findByName("phoneuser")).thenReturn(null);
        when(passwordEncoder.encode("password123")).thenReturn("encoded123");
        when(roleService.getRoleEntity("User")).thenReturn(roleEntity);

        UserEntity savedUser = new UserEntity("phoneuser", 987654321L, "encoded123", roleEntity);
        savedUser.setId(UUID.randomUUID());
        when(userRepository.save(any(UserEntity.class))).thenReturn(savedUser);

        UserDTO response = userService.register(registerRequest);

        assertNotNull(response);
        assertEquals("phoneuser", response.getName());
        assertEquals(987654321L, response.getPhoneNumber()); // Note mapper convert logic
    }

    @Test
    void register_UserAlreadyExists() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setName("testuser");
        registerRequest.setEmail_phoneNumber("test@test.com");
        registerRequest.setPassword("password123");

        when(userRepository.findByName("testuser")).thenReturn(userEntity);

        assertThrows(UserAlreadyExistsException.class, () -> userService.register(registerRequest));
    }

    @Test
    void register_InvalidPhoneFormat() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setName("newuser");
        registerRequest.setEmail_phoneNumber("0123");
        registerRequest.setPassword("password123");

        when(userRepository.findByName("newuser")).thenReturn(null);
        when(passwordEncoder.encode("password123")).thenReturn("encoded123");
        when(roleService.getRoleEntity("User")).thenReturn(roleEntity);

        assertThrows(IllegalArgumentException.class, () -> userService.register(registerRequest));
    }

    @Test
    void update_Success() {
        UserDTO updateRequest = new UserDTO();
        updateRequest.setName("updateduser");
        updateRequest.setEmail("updated@test.com");

        when(userRepository.findById(userEntity.getId())).thenReturn(Optional.of(userEntity));
        
        UserEntity updatedEntity = new UserEntity("updateduser", "updated@test.com", "encodedpassword", roleEntity);
        updatedEntity.setId(userEntity.getId());
        when(userRepository.save(any(UserEntity.class))).thenReturn(updatedEntity);

        UserDTO result = userService.update(updateRequest, userEntity.getId());

        assertNotNull(result);
        assertEquals("updateduser", result.getName());
        assertEquals("updated@test.com", result.getEmail());
    }

    @Test
    void update_UserNotFound() {
        UserDTO updateRequest = new UserDTO();
        UUID randomId = UUID.randomUUID();

        when(userRepository.findById(randomId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.update(updateRequest, randomId));
    }

    @Test
    void delete_Success() {
        when(userRepository.findById(userEntity.getId())).thenReturn(Optional.of(userEntity));
        
        UserEntity deletedEntity = new UserEntity("testuser", "test@test.com", "encodedpassword", roleEntity);
        deletedEntity.setId(userEntity.getId());
        deletedEntity.setDelete(true);
        when(userRepository.save(any(UserEntity.class))).thenReturn(deletedEntity);

        UserDTO result = userService.delete(userEntity.getId());

        assertNotNull(result);
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }
}
