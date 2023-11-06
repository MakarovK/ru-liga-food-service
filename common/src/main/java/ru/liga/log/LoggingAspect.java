package ru.liga.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class LoggingAspect {
    @Around("@annotation(Loggable)")
    public Object logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        System.out.println("Вызываемый метод: " + methodName);
        long startTime = System.currentTimeMillis();
        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable t) {
            System.out.println("Исключение в методе " + methodName + ": " + t.getMessage());
            throw t;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Метод " + methodName + " был выполнен за " + (endTime - startTime) + " мс.");
        return result;
    }

}
