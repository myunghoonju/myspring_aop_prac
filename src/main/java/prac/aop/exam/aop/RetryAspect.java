package prac.aop.exam.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import prac.aop.exam.annotation.Retry;

@Slf4j
@Aspect
public class RetryAspect {

    @Around("@annotation(retry)")
    public Object doRetry(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable {
        log.info("[retry] {} retry args {}", joinPoint.getSignature(), retry);

        Throwable ex = null;
        int val = retry.val();
        for (int i = 1; i <= val; i++) {
            try {
                log.info("retry cnt = {} / {}" ,i, val);
                return joinPoint.proceed();
            } catch (Throwable e) {
                ex = e;
            }
        }
        throw ex;
    }
}
