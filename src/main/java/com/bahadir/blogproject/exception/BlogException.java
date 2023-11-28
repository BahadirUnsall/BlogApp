package com.bahadir.blogproject.exception;

import lombok.Getter;

@Getter
public class BlogException extends RuntimeException{
    private final EErrorType errorType;

    public BlogException(EErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }
    public BlogException(EErrorType errorType,String message) {
        super(message);
        this.errorType = errorType;
    }

}
