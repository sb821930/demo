package com.example.demo.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDto<T> {

    private int statusCode;

    private String message;

    private T data;
}
