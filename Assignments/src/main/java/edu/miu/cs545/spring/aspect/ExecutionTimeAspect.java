package edu.miu.cs545.spring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExecutionTimeAspect {
    @Pointcut("(@annotation(ExecutionTime))")
    private void logExecutionTime() {}

    @Around("logExecutionTime()")
    public Object logAndExecute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long elapsedTime = System.currentTimeMillis() - start;
        System.out.println("%%%%%%%% Method " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + " ()" + " execution time : "
                + elapsedTime + " ms %%%%%%%%%");

        return result;
    }
}