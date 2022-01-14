package prac.aop.exam;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import prac.aop.exam.aop.RetryAspect;
import prac.aop.exam.aop.TraceAspect;

@Import({TraceAspect.class, RetryAspect.class} )
@Slf4j
@SpringBootTest
public class ExamTest {

    @Autowired
    ExamService service;

    @Test
    void test() {
        for (int i = 0; i < 5; i++) {
            log.info("test repeat :: {}", i);
            service.request("data" + i);
            log.info("test repeat end :: {}", i);
        }
    }
}
