package com.bahadir.blogproject.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum EErrorType {
    MISSING_PARAMETER(1001,"Parameter entered incorrectly",HttpStatus.BAD_REQUEST),
    NOT_FOUND(1002,"Requested item not found..",HttpStatus.NOT_FOUND),
    WRONG_FORMAT(1003,"The Date Format you entered must be 'yyyy-MM-dd'",HttpStatus.BAD_REQUEST);

    private int code;
    private String message;
    private HttpStatus httpStatus;
}
