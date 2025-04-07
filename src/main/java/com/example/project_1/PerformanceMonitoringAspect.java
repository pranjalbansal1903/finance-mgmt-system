package com.example.project_1;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class PerformanceMonitoringAspect {

    private static final Logger logger = LoggerFactory.getLogger(PerformanceMonitoringAspect.class);


    @Around("execution(* com.example.project_1.services.UserService.getUserByUsername(..))")
   public Object monitorExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
       long startTime = System.currentTimeMillis();
     Object result = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - startTime;
    logger.info("Method " + joinPoint.getSignature().getName() + " executed in " + executionTime + "ms");
        logger.info("------- Method '" + joinPoint.getSignature().getName() + "' executed in " + executionTime + "ms -------");
        return result;
    }
}

