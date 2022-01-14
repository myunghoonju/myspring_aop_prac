package prac.aop.pointcut.targetwithin;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Parent {

    public void methodP() {
        log.info("call parent");
    }
}
