package com.example.demo.exceptions;

import com.example.demo.rest.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ResponseDto> handleException(Exception e) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatusCode(200);
        responseDto.setMessage(e.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
