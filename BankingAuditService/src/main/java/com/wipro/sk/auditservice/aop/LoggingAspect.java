package com.wipro.sk.auditservice.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("within(com.wipro.sk.auditservice.controllers..*)")
    public void controllerMethods() {}

    @Pointcut("within(com.wipro.sk.auditservice.services..*)")
    public void serviceMethods() {}

    @Before("controllerMethods() || serviceMethods()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("➡ Entering method: {} with args: {}", 
                 joinPoint.getSignature(), 
                 joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "controllerMethods() || serviceMethods()", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        log.info("⬅ Exiting method: {} with result: {}", 
                 joinPoint.getSignature(), 
                 result);
    }
}
