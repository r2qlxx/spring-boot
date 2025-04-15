package com.example.demo.domain.service;

import org.springframework.stereotype.Service;

@Service
public class RestService {

    public String doSomething() {
        return "Rest Service";
    }

    public String doSomething(int id) {
        return "Rest Service";
    }
}
