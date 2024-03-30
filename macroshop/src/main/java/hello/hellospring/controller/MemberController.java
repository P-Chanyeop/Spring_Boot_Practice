package hello.hellospring.controller;


import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller // 애너테이션이 존재하면 스프링 컨테이너에 Controller 객체(빈)을 생성하여 담아두고, 그것을 스프링 컨테이너가 관리하게된다.
public class MemberController {

    // 스프링 컨테이너에 존재하는 controller 객체를 받아와 사용할 수 있도록 해야한다.
    // => 이게 그 유명한 DI(Dependency Injection)이다. 의존관게를 밖에서(스프링이) 주입
    private final MemberService memberService;

    @Autowired  // 생성자에 Autowired가 있으면 스프링 컨테이너에 있는 memberService를 가져다 연결시켜준다.
    public MemberController(MemberService memberService) {  // service가 순수한 자바 코드의 경우 스프링 컨테이너가 service를 인식하지 못해 에러가 발생.
        this.memberService = memberService;
    }
}
