package prac.aop.pointcut.targetwithin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configurations {

    @Bean
    public Parent parent() {
        return new Parent();
    }

    @Bean
    public Child child() {
        return new Child();
    }

    @Bean
    public Aspects atTargetWithin() {
        return new Aspects();
    }
}
