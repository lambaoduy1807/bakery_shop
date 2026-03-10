package com.bakery_shop.exception;

import com.bakery_shop.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(UserNotFoundException.class)
    public ApiResponse<?> handleUserNotFound(UserNotFoundException ex) {
        return ApiResponse.error(
                HttpStatus.NOT_FOUND.value(),
                "USER_NOT_FOUND",
                ex.getMessage()
        );
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ApiResponse<?> handleUserExists(UserAlreadyExistsException ex) {
        return ApiResponse.error(
                HttpStatus.BAD_REQUEST.value(),
                "USER_ALREADY_EXISTS",
                ex.getMessage()
        );
    }

    @ExceptionHandler(InvalidCredentialException.class)
    public ApiResponse<?> handleInvalidCredential(InvalidCredentialException ex) {
        return ApiResponse.error(
                HttpStatus.UNAUTHORIZED.value(),
                "INVALID_CREDENTIAL",
                ex.getMessage()
        );
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<?> handleException(Exception ex) {
        return ApiResponse.error(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "INTERNAL_SERVER_ERROR",
                ex.getMessage()
        );
    }
}