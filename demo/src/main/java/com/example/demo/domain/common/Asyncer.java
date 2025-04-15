package com.example.demo.domain.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class Asyncer {

    @Async
    public CompletableFuture<String> async() {
        heavyTask();
        return CompletableFuture.completedFuture("Async.");
    }

    public void heavyTask() {
        log.info("heavy task before");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
        log.info("heavy task after");
    }
}
