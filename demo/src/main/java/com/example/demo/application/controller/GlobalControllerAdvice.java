package com.example.demo.application.controller;

import com.example.demo.application.resource.ErrorRes;
import com.example.demo.domain.common.AppLogger;
import com.example.demo.domain.exception.SystemException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(SystemException.class)
    // @ExceptionHandler(SystemException.class, SystemException2.class, ...)
    public ResponseEntity<ErrorRes> handleException(SystemException e) {
        String errMsg = AppLogger.readAppLogMsg("ERROR_001", e.getArgs());
        ErrorRes errorRes = new ErrorRes(e.getErrorCode(), errMsg);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(errorRes, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
