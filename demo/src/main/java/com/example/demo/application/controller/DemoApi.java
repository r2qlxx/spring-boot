package com.example.demo.application.controller;

import com.example.demo.application.resource.RestRes;
import com.example.demo.domain.service.DemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/demo")
public class DemoApi {
    private final DemoService demoService;

    @GetMapping("/http")
    public ResponseEntity<Void> http() {
        demoService.http();
        return new ResponseEntity<>(null, null, HttpStatus.OK);
    }

    @GetMapping("/crud")
    public ResponseEntity<Void> crud() {
        demoService.crud();
        return new ResponseEntity<>(null, null, HttpStatus.OK);
    }

    @GetMapping("/error")
    public ResponseEntity<Void> error() {
        demoService.error();
        return new ResponseEntity<>(null, null, HttpStatus.OK);
    }

    @GetMapping("/aop")
    public ResponseEntity<Void> aop() {
        demoService.before();
        demoService.after();
        demoService.afterReturning();
        try {
            demoService.afterThrowing();
        } catch (Exception e) {
            // snip
        }
        demoService.around();
        return new ResponseEntity<>(null, null, HttpStatus.OK);

        // LOG
        // 2025-04-14T15:13:04.225+09:00  INFO 1280 --- [demo] [nio-8080-exec-1] com.example.demo.aop.SpringAspect        : AOP before
        // 2025-04-14T15:13:04.225+09:00  INFO 1280 --- [demo] [nio-8080-exec-1] c.e.demo.domain.service.DemoService      : DemoService before
        // 2025-04-14T15:13:04.226+09:00  INFO 1280 --- [demo] [nio-8080-exec-1] c.e.demo.domain.service.DemoService      : DemoService after
        // 2025-04-14T15:13:04.226+09:00  INFO 1280 --- [demo] [nio-8080-exec-1] com.example.demo.aop.SpringAspect        : AOP after
        // 2025-04-14T15:13:04.226+09:00  INFO 1280 --- [demo] [nio-8080-exec-1] c.e.demo.domain.service.DemoService      : DemoService afterReturning
        // 2025-04-14T15:13:04.226+09:00  INFO 1280 --- [demo] [nio-8080-exec-1] com.example.demo.aop.SpringAspect        : AOP afterReturning
        // 2025-04-14T15:13:04.226+09:00  INFO 1280 --- [demo] [nio-8080-exec-1] c.e.demo.domain.service.DemoService      : DemoService afterThrowing
        // 2025-04-14T15:13:04.226+09:00  INFO 1280 --- [demo] [nio-8080-exec-1] com.example.demo.aop.SpringAspect        : AOP afterThrowing
        // 2025-04-14T15:13:04.226+09:00  INFO 1280 --- [demo] [nio-8080-exec-1] com.example.demo.aop.SpringAspect        : AOP around_before
        // 2025-04-14T15:13:04.227+09:00  INFO 1280 --- [demo] [nio-8080-exec-1] c.e.demo.domain.service.DemoService      : DemoService around
        // 2025-04-14T15:13:04.227+09:00  INFO 1280 --- [demo] [nio-8080-exec-1] com.example.demo.aop.SpringAspect        : AOP around_after
    }


    @GetMapping("/async")
    public ResponseEntity<Void> async() {
        demoService.async();
        return new ResponseEntity<>(null, null, HttpStatus.OK);
    }

    @GetMapping("/cache")
    public ResponseEntity<Void> cache() {
        demoService.cache();
        return new ResponseEntity<>(null, null, HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<Void> profile() {
        demoService.profile();
        return new ResponseEntity<>(null, null, HttpStatus.OK);
    }

    @GetMapping("/retry")
    public ResponseEntity<Void> retry() {
        demoService.retry();
        return new ResponseEntity<>(null, null, HttpStatus.OK);
    }

    // ---------------------------------
    // -- Sample Method For Unit Test --
    // ---------------------------------
    @GetMapping("/unit/get")
    public ResponseEntity<RestRes> unitGet() {
        String content = demoService.doSomething();
        RestRes restRes = new RestRes(content);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(restRes, headers, HttpStatus.OK);
    }

}
