package com.example.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class SpringAspect {

    @Before("execution(* com.example.demo.domain.service.DemoService.before(..))")
    public void before(JoinPoint jp) {
        log.info("AOP before");
    }

    @After("execution(* com.example.demo.domain.service.DemoService.after(..))")
    public void after(JoinPoint jp) {
        log.info("AOP after");
    }

    @AfterReturning("execution(* com.example.demo.domain.service.DemoService.afterReturning(..))")
    public void afterReturning(JoinPoint jp) {
        log.info("AOP afterReturning");
    }

    @AfterThrowing("execution(* com.example.demo.domain.service.DemoService.afterThrowing(..))")
    public void afterThrowing(JoinPoint jp) {
        log.info("AOP afterThrowing");
    }

    @Around("execution(* com.example.demo.domain.service.DemoService.around(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object result = null;
        try {
            log.info("AOP around_before");
            result = pjp.proceed();
            log.info("AOP around_after");
        } catch (Throwable e) {
            throw e;
        }

        return result;
    }

    @Around("@annotation(com.example.demo.aop.annotation.ProcTime)")
    public Object procTime(ProceedingJoinPoint pjp) throws Throwable {
        try {
            long start_ms = System.currentTimeMillis();
            Object result = pjp.proceed();
            long procTime_ms = System.currentTimeMillis() - start_ms;
            String className = pjp.getTarget().getClass().getCanonicalName();
            String methodName = pjp.getSignature().getName();
            log.info(className + "." + methodName + "() ended. ProcTime = " + procTime_ms + " ms.");
            return result;
        } catch (Throwable e) {
            throw e;
        }
    }
}
