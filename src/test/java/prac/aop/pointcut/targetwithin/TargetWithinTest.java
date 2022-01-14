package prac.aop.pointcut.targetwithin;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
@Import(Configurations.class)
public class TargetWithinTest {

    @Autowired
    Child child;

    @Test
    void success() {
        log.info("proxy of child = {}", child.getClass());
        child.methodC();
        child.methodP();
    }
}
