package prac.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AspectV4Pointcut {

    @Around("prac.aop.order.aop.Pointcuts.allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[LOG] {}", joinPoint.getSignature());
        return joinPoint.proceed();
    }


    @Around("prac.aop.order.aop.Pointcuts.orderAndService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            log.info("[TRANSACTION START LOG] {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            log.info("[TRANSACTION COMMIT LOG] {}", joinPoint.getSignature());
            return result;

        } catch (Exception e) {
            log.info("[TRANSACTION ROLLBACK LOG] {}", joinPoint.getSignature());
            throw e;

        } finally {
            log.info("[TRANSACTION RESOURCE RELEASE LOG] {}", joinPoint.getSignature());
        }
    }
}
