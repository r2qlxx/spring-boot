package com.example.demo.application.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorRes {
    private String errorCode;
    private String errorMessage;
}
