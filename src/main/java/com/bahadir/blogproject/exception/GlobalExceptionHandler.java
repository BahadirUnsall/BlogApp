package com.bahadir.blogproject.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleRuntimeException(RuntimeException exception){
        return ResponseEntity.ok(ErrorMessage.builder()
                        .code(1001)
                        .message("Unexpected Runtime Error" + exception.getMessage())
                        .build());
    }

    @ExceptionHandler(BlogException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleBlogException(BlogException exception){
        return ResponseEntity
                .status(exception.getErrorType().getHttpStatus())
                .body(ErrorMessage.builder()
                        .code(exception.getErrorType().getCode())
                        .message(exception.getMessage())
                        .build());
    }

}
