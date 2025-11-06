package com.example.schedulerproject.exception;

import com.example.schedulerproject.dto.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

/**
 * 예외를 전역적으로 처리하는 클래스
 * 발생하는 예외를 한 곳에서 처리하고 JSON 형태로 응답 반환
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // ResponseStatusException 발생 시 처리
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseStatusException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                ex.getStatusCode().value(),
                ex.getStatusCode().toString(),
                ex.getReason(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(errorResponse, ex.getStatusCode());
    }

    // 유효성 검사 실패 시 처리 (@Valid 관련 예외)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatusException(MethodArgumentNotValidException ex, WebRequest request) {

        String message = "Validation failed";

        FieldError fieldError = ex.getBindingResult().getFieldError();
        if(fieldError != null) {
            message = fieldError.getDefaultMessage();
        }

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                ex.getStatusCode().value(),
                ex.getStatusCode().toString(),
                message,
                request.getDescription(false)
        );

        return new ResponseEntity<>(errorResponse, ex.getStatusCode());
    }
}