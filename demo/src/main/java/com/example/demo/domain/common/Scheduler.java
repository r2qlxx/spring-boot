package com.example.demo.domain.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Scheduler {

    @Scheduled(initialDelay = 5000, fixedRate = 300000) // ms
    public void schedule() {
        log.info("scheduling.");
    }

    // Log
    // 2025-04-14T14:48:56.300+09:00  INFO 14952 --- [demo] [   scheduling-1] c.example.demo.domain.common.Scheduler   : scheduling.
    // 2025-04-14T14:49:26.298+09:00  INFO 14952 --- [demo] [   scheduling-1] c.example.demo.domain.common.Scheduler   : scheduling.
    // 2025-04-14T14:49:56.310+09:00  INFO 14952 --- [demo] [   scheduling-1] c.example.demo.domain.common.Scheduler   : scheduling.
    // ...
}
