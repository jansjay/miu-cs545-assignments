package edu.miu.cs545.spring.aspect;

import edu.miu.cs545.spring.models.Exception;
import edu.miu.cs545.spring.repositories.ExceptionRepository;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
@Aspect
public class ExceptionAspect {
    @Autowired
    ExceptionRepository exceptionRepository;
    @Pointcut("execution(* edu.miu.cs545.spring.controllers.*.*(..)) && " +
            "!execution(* edu.miu.cs545.spring.repositories.ExceptionRepository.*(..)) &&" +
            "!execution(* edu.miu.cs545.spring.aspect.ExceptionAspect.*(..))")
    private void logException() {}

    @AfterThrowing(pointcut="logException()", throwing = "ex")
    public void catchAndLogException(Throwable ex) {
        StackTraceElement lastTrace = ex.getStackTrace()[0];
        storeExceptionEntry(lastTrace.getClassName()+"."+lastTrace.getMethodName(),
                SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString(),
                ex.getClass().getCanonicalName());
    }

    private void storeExceptionEntry(String operation, String user, String exceptionType){
        Exception exception = new Exception();
        exception.setDate(LocalDate.now());
        exception.setTime(LocalTime.now());
        exception.setPrinciple(user);
        exception.setOperation(operation);
        exception.setExceptionType(exceptionType);
        exceptionRepository.save(exception);
    }
}