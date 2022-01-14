package prac.aop.pointcut;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import prac.aop.member.MemberServiceImpl;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ExecutionTest {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method method;

    @BeforeEach
    public void init() throws NoSuchMethodException {
         method = MemberServiceImpl.class.getMethod("call", String.class);

    }

    @Test
    @DisplayName("public java.lang.String prac.aop.member.MemberServiceImpl.call(java.lang.String)")
    void printMethod() {
         log.debug("method = {}", method);
    }

    @Test
    @DisplayName("100%match")
    void exactMatch() {
         pointcut.setExpression("execution(public java.lang.String prac.aop.member.MemberServiceImpl.call(String))");
         assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("shortest expressions")
    void allMatch() {
        pointcut.setExpression("execution(* *(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("nameMatch")
    void nameMatch() {
        pointcut.setExpression("execution(* call(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("patternMatch")
    void patternMatch() {
        pointcut.setExpression("execution(* *al*(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("packageMatch")
    void packageMatch() {
        pointcut.setExpression("execution(* prac.aop.*.*(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isFalse();
    }

    @Test
    @DisplayName("subPackageMatch")
    void subPackageMatch() {
        pointcut.setExpression("execution(* prac.aop..*.*(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("typeExactMatch1")
    void typeExactMatch1() {
        pointcut.setExpression("execution(* prac.aop.member.MemberService.*(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("typeExactMatch2")
    void typeExactMatch2() {
        pointcut.setExpression("execution(* prac.aop.member.MemberServiceImpl.*(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("typeExactMatch3")
    void typeExactMatch3() throws NoSuchMethodException {
        pointcut.setExpression("execution(* prac.aop.member.MemberServiceImpl.*(..))");
        Method internal = MemberServiceImpl.class.getMethod("internal", String.class);

        assertThat(pointcut.matches(internal, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("ONLY AFFECT PARENT METHODS")
    void typeExactMatch4() throws NoSuchMethodException {
        pointcut.setExpression("execution(* prac.aop.member.MemberService.*(..))");
        // internal :: MemberServiceImpl only
        Method internal = MemberServiceImpl.class.getMethod("internal", String.class);

        assertThat(pointcut.matches(internal, MemberServiceImpl.class)).isFalse();
    }

    @Test
    @DisplayName("typeExactMatch5")
    void typeExactMatch5() {
        pointcut.setExpression("execution(* *(String))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("typeExactMatch6")
    void typeExactMatch6() {
        pointcut.setExpression("execution(* *())");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isFalse();
    }

    @Test
    @DisplayName("one param")
    void typeExactMatch7() {
        pointcut.setExpression("execution(* *(*))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("various param")
    void typeExactMatch8() {
        pointcut.setExpression("execution(* *(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("String param and else")
    void typeExactMatch9() {
        pointcut.setExpression("execution(* *(String, ..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("two params::String and else")
    void typeExactMatch10() {
        pointcut.setExpression("execution(* *(String,*))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }
}