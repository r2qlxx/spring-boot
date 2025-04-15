package com.example.demo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "web")
@Getter
@Setter
public class WebProps {
    private long connectTimeoutMillis;
    private long connectionRequestTimeoutMillis;
    private long readTimeoutMillis;
    private int maxTotal;
    private int defaultMaxPerRoute;
    private long keepAliveTimeMillis;
    private long evictIdleConnectionTimeMillis;
    private String baseUrl;
    private String path;
}