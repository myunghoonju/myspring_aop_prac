package prac.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Slf4j
@Aspect
public class AspectV6Advice {

    @Around("prac.aop.order.aop.Pointcuts.orderAndService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            log.info("[TRANSACTION START LOG] {}", joinPoint.getSignature());
            // @Before
            Object result = joinPoint.proceed();
            log.info("[TRANSACTION COMMIT LOG] {}", joinPoint.getSignature());
            // @AfterReturning
            return result;

        } catch (Exception e) {
            log.info("[TRANSACTION ROLLBACK LOG] {}", joinPoint.getSignature());
            // @AfterThrowing
            throw e;

        } finally {
            log.info("[TRANSACTION RESOURCE RELEASE LOG] {}", joinPoint.getSignature());
            // @After
        }
    }

    @Before("prac.aop.order.aop.Pointcuts.orderAndService()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("[TRANSACTION @Before LOG] {}", joinPoint.getSignature());
    }

    @AfterReturning(value = "prac.aop.order.aop.Pointcuts.orderAndService()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("[TRANSACTION @AfterReturning LOG] {}, return = {}", joinPoint.getSignature(), result.toString());
    }

    @AfterThrowing(value = "prac.aop.order.aop.Pointcuts.orderAndService()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        log.info("[TRANSACTION @AfterThrowing LOG] {}, e = {}", joinPoint.getSignature(), e);
    }

    @After("prac.aop.order.aop.Pointcuts.orderAndService()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("[TRANSACTION @After LOG] {}", joinPoint.getSignature());
    }
}
