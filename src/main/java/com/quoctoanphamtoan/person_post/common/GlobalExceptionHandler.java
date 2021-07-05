package com.quoctoanphamtoan.person_post.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity handleException(Exception e){

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setError("Server Error....."+e.getLocalizedMessage()+"......!");
        apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(apiResponse);
    }

    @ExceptionHandler
    public ResponseEntity handleBadRequestException(BadRequestException e){

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        apiResponse.setError(e.getErrors());
        return ResponseEntity.status(400).body(apiResponse);
    }


}
