package com.woorifisa.board.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    // @Before("execution(* com.woorifisa.board.domain.*.controller.*.*(..))")
    // public void logBefore(JoinPoint jp) {
    //     log.info("===== AOP =====");
    //     log.info("Method = {}", jp.getSignature().getName());
    // }

    @Around("execution(* com.woorifisa.board.domain.*.controller.*.*(..))")
    public Object logAround(ProceedingJoinPoint pjp) throws Throwable {

        log.info("===== AOP Start =====");
        log.info(pjp.getSignature().toLongString());

        Object proceed = pjp.proceed();

        log.info("===== AOP Finish =====");

        return proceed;
    }

}
