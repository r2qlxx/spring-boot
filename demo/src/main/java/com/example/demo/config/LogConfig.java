package com.example.demo.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.nio.charset.StandardCharsets;

@Configuration
public class LogConfig {
    @Bean
    public MessageSource appLog() {
        ResourceBundleMessageSource rbms = new ResourceBundleMessageSource();
        rbms.addBasenames("applog");
        rbms.setDefaultEncoding(StandardCharsets.UTF_8.name());
        return rbms;
    }
}
