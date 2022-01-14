package prac.aop.exam;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import prac.aop.exam.annotation.Retry;
import prac.aop.exam.annotation.Trace;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository repository;

    @Trace @Retry(val = 4)
    public void request(String itemId) {
        repository.save(itemId);
    }
}
