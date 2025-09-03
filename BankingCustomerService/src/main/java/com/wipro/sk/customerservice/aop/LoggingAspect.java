package com.wipro.sk.customerservice.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    // Pointcut for all methods inside CustomerController
    @Pointcut("execution(* com.wipro.sk.customerservice.controllers.CustomerController.*(..))")
    public void customerControllerMethods() {}

    // Before method execution
    @Before("customerControllerMethods()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Entering method: {} with arguments: {}",
                joinPoint.getSignature().getName(),
                joinPoint.getArgs());
    }

    // After method execution
    @After("customerControllerMethods()")
    public void logAfter(JoinPoint joinPoint) {
        log.info("Exiting method: {}", joinPoint.getSignature().getName());
    }
}
