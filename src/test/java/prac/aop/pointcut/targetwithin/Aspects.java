package prac.aop.pointcut.targetwithin;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class Aspects {

    @Around("execution(* prac.aop..*(..)) && @target(prac.aop.member.annotation.ClassAop)")
    public Object atTarget(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[atTarget] {}", joinPoint.getSignature());
        return joinPoint.proceed();
    }

    @Around("execution(* prac.aop..*(..)) && @within(prac.aop.member.annotation.ClassAop)")
    public Object atWithin(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[atWithin] {}", joinPoint.getSignature());
        return joinPoint.proceed();
    }
}
