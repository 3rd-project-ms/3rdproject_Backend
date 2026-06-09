package com.example._rdproject.exception;

import com.example._rdproject.dto.common.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 아이디 중복 예외가 발생하면 이 메서드가 실행됨
    @ExceptionHandler(DuplicateIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResponse<?> handleDuplicateIdException(DuplicateIdException e) {
        return CommonResponse.error("ERR_DUPLICATE_LOGIN_ID", e.getMessage());
    }
}
