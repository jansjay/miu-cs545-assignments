package edu.miu.cs545.spring.aspect;

import edu.miu.cs545.spring.models.Logger;
import edu.miu.cs545.spring.repositories.LoggerRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalTime;

@Component
@Aspect
public class LoggerAspect {
    @Autowired
    private LoggerRepository loggerRepository;
    @Pointcut("execution(* edu.miu.cs545.spring..*.*(..)) && " +
            "!execution(* edu.miu.cs545.spring.repositories.LoggerRepository.*(..)) &&" +
            "!execution(* edu.miu.cs545.spring.aspect.LoggerAspect.*(..))")
    private void log() {}
    @Before("log()")
    public void beforeLog(JoinPoint joinPoint){
        storeLogEntry("DUMMY",MessageFormat.format("BEFORE: {0}.{1}", joinPoint.getTarget(), joinPoint.getSignature()));
    }

    @After("log()")
    public void afterLog(JoinPoint joinPoint){
        storeLogEntry("DUMMY", MessageFormat.format("AFTER: {0}.{1}", joinPoint.getTarget(), joinPoint.getSignature()));
    }

    private void storeLogEntry(String operation, String user){
        if(loggerRepository == null){
            return;
        }
        Logger logger = new Logger();
        logger.setDate(LocalDate.now());
        logger.setTime(LocalTime.now());
        logger.setPrinciple(user);
        logger.setOperation(operation);
        loggerRepository.save(logger);
    }
}
