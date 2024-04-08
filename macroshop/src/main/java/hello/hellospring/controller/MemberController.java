package hello.hellospring.controller;


import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller // 애너테이션이 존재하면 스프링 컨테이너에 Controller 객체(빈)을 생성하여 담아두고, 그것을 스프링 컨테이너가 관리하게된다.
public class MemberController {

    // 스프링 컨테이너에 존재하는 controller 객체를 받아와 사용할 수 있도록 해야한다.
    // => 이게 그 유명한 DI(Dependency Injection)이다. 의존관게를 밖에서(스프링이) 주입
    // DI에는 필드주입, setter 주입, 생성자 주입이 존재한다. 의존관계가 실행중에 동적으로 변하는 경우는 거의없으므로 생성자 주입을 권장한다.

    // 필드주입. 수정자 주입과 같은 이미 로드된 상태에서의 바꿔치기가 불가능하다. 생성자 주입의 경우는 컨테이너 의존없이 테스트할 때 객체를 주입할 수 있다는 점이 있다.
//    @Autowired private MemberService memberService;

    // setter 주입. public 하게 노출되기 때문에, 비추천
//     private MemberService memberService;
//    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }

    private final MemberService memberService;

    @Autowired  // 생성자에 Autowired가 있으면 스프링 컨테이너에 있는 memberService를 가져다 연결시켜준다.
    public MemberController(MemberService memberService) {  // service가 순수한 자바 코드의 경우 스프링 컨테이너가 service를 인식하지 못해 에러가 발생.
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm.html";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member = " + member.getName());
        memberService.join(member);

        return "redirect:/";
    }
}
