package prac.aop.pointcut.targetwithin;

import lombok.extern.slf4j.Slf4j;
import prac.aop.member.annotation.ClassAop;

@Slf4j
@ClassAop
public class Child extends Parent {

    public void methodC() {
        log.info("call child");
    }
}
