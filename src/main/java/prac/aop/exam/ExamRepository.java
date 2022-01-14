package prac.aop.exam;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import prac.aop.exam.annotation.Retry;
import prac.aop.exam.annotation.Trace;

@Slf4j
@Repository
public class ExamRepository {

    private static int seq = 0;

    @Trace @Retry(val = 4)
    public String save(String itemId) {
        log.error("before seq = {}", seq);
        seq++;
        log.error("after seq = {}", seq);
        if (seq % 5 == 0) {
            throw new IllegalStateException("exceed limit");
        }
        return "ok";
    }
}
