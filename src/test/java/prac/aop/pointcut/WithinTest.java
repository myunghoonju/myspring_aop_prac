package prac.aop.pointcut;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import prac.aop.member.MemberServiceImpl;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

public class WithinTest {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method method;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        method = MemberServiceImpl.class.getMethod("call", String.class);
    }

    @Test
    void withinExact() {
        pointcut.setExpression("within(prac.aop.member.MemberServiceImpl)");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void withinStar() {
        pointcut.setExpression("within(prac.aop.member.*Service*)");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void withinSubPackage() {
        pointcut.setExpression("within(prac.aop..*)");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("target type should be the same")
    void withinSuperTypeFalse() {
        pointcut.setExpression("within(prac.aop.member.MemberService)");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isFalse();
    }
}
