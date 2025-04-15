package com.example.demo.domain.common;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class ProdProfile implements ProfileIf {

    @Override
    public String doSomething() {
        return "Prod Profile";
    }
}
