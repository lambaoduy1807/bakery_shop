package com.bakery_shop.model.response;

import com.bakery_shop.model.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenResponse {
    String refreshToken;
    String accessToken;
    UserDTO user;
}
