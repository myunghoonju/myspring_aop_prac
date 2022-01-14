package prac.aop.pointcut;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import prac.aop.member.MemberServiceImpl;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

public class ArgsTest {

    Method method;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        method = MemberServiceImpl.class.getMethod("call", String.class);
    }

    private AspectJExpressionPointcut pointcut(String expression) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(expression);

        return pointcut;
    }

    @Test
    @DisplayName("args:: 런타임에 전달된 인수로 판단 (동적)")
    void args() {
        assertThat(pointcut("args(String)").matches(method, MemberServiceImpl.class)).isTrue();
        assertThat(pointcut("args(Object)").matches(method, MemberServiceImpl.class)).isTrue();
        assertThat(pointcut("args()").matches(method, MemberServiceImpl.class)).isFalse();
        assertThat(pointcut("args(..)").matches(method, MemberServiceImpl.class)).isTrue();
        assertThat(pointcut("args(*)").matches(method, MemberServiceImpl.class)).isTrue();
        assertThat(pointcut("args(String,..)").matches(method, MemberServiceImpl.class)).isTrue();
    }


    @Test
    @DisplayName("execution:: 메서드의 시그니처로 판단 (정적)")
    void compareExecution() {
        assertThat(pointcut("args(java.io.Serializable)").matches(method, MemberServiceImpl.class)).isTrue();
        assertThat(pointcut("execution(* *(java.io.Serializable))").matches(method, MemberServiceImpl.class)).isFalse();
        assertThat(pointcut("execution(* *(Object))").matches(method, MemberServiceImpl.class)).isFalse();
    }
}
