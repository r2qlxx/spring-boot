package com.example.demo.domain.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LifeCycler {

    @PostConstruct
    public void postConstruct() {
        log.info("Post Construct.");
    }

    @PreDestroy
    public void preDestroy() {
        log.info("Pre Destroy.");
    }

    // Log
    // --- : Post Construct.
    // --- : Tomcat started on port(s): 8080 (http) with context path ''
    // --- : Started DemoApplication in 1.884 seconds (JVM running for 2.381)
    // --- : Application shutdown requested.
    // --- : Pre Destroy.
}