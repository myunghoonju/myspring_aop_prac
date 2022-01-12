package prac.aop;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import prac.aop.order.OrderRepository;
import prac.aop.order.OrderService;
import prac.aop.order.aop.AspectV1;
import prac.aop.order.aop.AspectV2;
import prac.aop.order.aop.AspectV3;
import prac.aop.order.aop.AspectV4Pointcut;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

//@Import(AspectV1.class)
//@Import(AspectV2.class)
//@Import(AspectV3.class)
@Import(AspectV4Pointcut.class)
@Slf4j
@SpringBootTest
public class AopTest {

    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    void aopInfo() {
        log.info("isAopProxy orderService = {}", AopUtils.isAopProxy(orderService));
        log.info("isAopProxy orderRepository = {}",AopUtils.isAopProxy(orderRepository));
    }

    @Test
    void success() {
        orderService.orderItem("itemA");
    }

    @Test
    void exception() {
        assertThatThrownBy(() -> orderService.orderItem("ex")).isInstanceOf(IllegalStateException.class);
    }
}
