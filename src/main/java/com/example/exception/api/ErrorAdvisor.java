package com.example.exception.api;

import com.example.exception.exception.BaseException;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

//@ControllerAdvice
@RestControllerAdvice
public class ErrorAdvisor {

    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ErrorResponse handleBaseException(BaseException e){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(e.getMessage());
        errorResponse.setStatus(HttpStatus.EXPECTATION_FAILED.value());
        return errorResponse;

    }

//    @ExceptionHandler(BaseException.class)
//    public ResponseEntity<ErrorResponse> handleBaseException(BaseException e){
//        ErrorResponse errorResponse = new ErrorResponse();
//        errorResponse.setError(e.getMessage());
//        errorResponse.setStatus(HttpStatus.EXPECTATION_FAILED.value());
//        return  new ResponseEntity<>(errorResponse, HttpStatus.EXPECTATION_FAILED);
//    }

    @Data
    public static class ErrorResponse{
        private LocalDateTime localDateTime = LocalDateTime.now();
        private int status;
        private String error;
    }
}
