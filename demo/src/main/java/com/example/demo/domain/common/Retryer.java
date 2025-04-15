package com.example.demo.domain.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Retryer {

    @Retryable(retryFor = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public void retry() throws Exception {
        log.info("retry");
        throw new Exception();
    }

    @Recover
    public void recover(Exception e) {
        log.info("recover");
    }
}
