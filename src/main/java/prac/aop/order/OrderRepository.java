package prac.aop.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class OrderRepository {

    public String save(String itemId) {
        log.info("start [OrderRepository]");
        if (itemId.equals("ex")) {
            throw new IllegalStateException("error");
        }
        return "ok";
    }
}
