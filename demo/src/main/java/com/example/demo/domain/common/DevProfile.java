package com.example.demo.domain.common;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DevProfile implements ProfileIf {

    @Override
    public String doSomething() {
        return "Dev Profile";
    }
}
