package com.bakery_shop.security;

import com.bakery_shop.model.JwtUserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;
public class SecurityUtil {

    public static JwtUserPrincipal getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        return (JwtUserPrincipal) authentication.getPrincipal();
    }

    public static UUID getCurrentUserId() {
        return getCurrentUser().getUserId();
    }

}