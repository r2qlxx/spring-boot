package com.example.demo.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SystemException extends RuntimeException {
    private Throwable e;
    private String errorCode;
    private Object[] args;

    public SystemException(String errorCode, Object[] args) {
        this.errorCode = errorCode;
        this.args = args;
    }
}
