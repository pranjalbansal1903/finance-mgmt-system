package com.example.project_1;

import com.example.project_1.models.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;





    @Aspect
    @Component
    public class UserActionLoggingAOP {

        private static final Logger logger = LoggerFactory.getLogger(UserActionLoggingAOP.class);


        @Around("execution(* com.example.project_1.services.UserService.createUser(..)) || " +
                "execution(* com.example.project_1.services.UserService.deleteUserById(..)) ||" +
          "execution(* com.example.project_1.services.ExpenseService.createExpense(..)) ||" +
                "execution(* com.example.project_1.services.ExpenseService.deleteExpense(..))"
        )


        public Object logUserAction(ProceedingJoinPoint joinPoint) throws Throwable {
//

            String methodName = joinPoint.getSignature().getName();
            logger.info("--------Executing method: {}", methodName);

            Object result;
            try {
                result = joinPoint.proceed();
            } catch (Exception ex) {
                logger.error("------Exception in method: {} - Error: {}", methodName, ex.getMessage());
                throw ex;
            }

            logger.info("-------Finished executing method: {}", methodName);
            return result;
        }
    }


