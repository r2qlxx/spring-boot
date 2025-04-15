package com.example.demo.domain.common;

import com.example.demo.domain.object.CacheKey;
import com.example.demo.domain.object.CacheValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Cacher {

    @Cacheable("name")
    public CacheValue cache(CacheKey cacheKey) {
        heavyTask();
        return new CacheValue("Value");
    }

    private void heavyTask() {
        try {
            log.info("heavy task started.");
            Thread.sleep(3000);
            log.info("heavy task ended.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
