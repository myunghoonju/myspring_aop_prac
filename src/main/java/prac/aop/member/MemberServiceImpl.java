package prac.aop.member;

import org.springframework.stereotype.Component;
import prac.aop.member.annotation.ClassAop;
import prac.aop.member.annotation.MethodAop;

@ClassAop
@Component
public class MemberServiceImpl implements MemberService {

    @Override
    @MethodAop("test val")
    public String call(String param) {
        return "ok";
    }


    public String internal(String param) {
        return "ok";
    }

}
