package prac.aop.pointcut;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import prac.aop.member.MemberService;
import prac.aop.member.annotation.ClassAop;
import prac.aop.member.annotation.MethodAop;

@Import(ParameterTest.ParameterAspect.class)
@Slf4j
@SpringBootTest
public class ParameterTest {

    @Autowired
    MemberService memberService;

    @Test
    void success() {
        log.info("memberService proxy = {}", memberService.getClass());
        memberService.call("helloA");
    }

    @Slf4j
    @Aspect
    static class ParameterAspect {
        @Pointcut("execution(* prac.aop.member..*.*(..))")
        private void allMember() {}

        @Around("allMember()")
        public Object logArgs1(ProceedingJoinPoint joinPoint) throws Throwable {
            Object arg1 = joinPoint.getArgs()[0];
            log.info("[logArgs1] {}, args = {}", joinPoint.getSignature(), arg1);

            return joinPoint.proceed();
        }

        @Around("allMember() && args(arg,..)")
        public Object logArgs2(ProceedingJoinPoint joinPoint, Object arg) throws Throwable {
            log.info("[logArgs2] {}, args = {}", joinPoint.getSignature(), arg);

            return joinPoint.proceed();
        }

        @Before("allMember() && args(arg,..)")
        public void logArgs3(String arg) {
            log.info("[logArgs3] args = {}", arg);
        }

        @Before("allMember() && this(obj)")
        public void logArgs4(MemberService obj) {
            log.info("[logArgs4] obj = {}", obj.getClass());
        }

        @Before("allMember() && target(obj)")
        public void logArgs5(MemberService obj) {
            log.info("[logArgs5] obj = {}", obj.getClass());
        }

        @Before("allMember() && @target(annotation)")
        public void logArgs6(ClassAop annotation) {
            log.info("[logArgs6] annotation = {}", annotation);
        }

        @Before("allMember() && @within(annotation)")
        public void logArgs7(ClassAop annotation) {
            log.info("[logArgs7] annotation = {}", annotation);
        }

        @Before("allMember() && @annotation(annotation)")
        public void logArgs8(MethodAop annotation) {
            log.info("[logArgs8] annotation = {}", annotation.value());
        }
    }
}
