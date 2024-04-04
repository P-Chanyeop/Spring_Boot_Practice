package hello.hellospring;


import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  // 자바 코드로 직접 스프링 빈에 등록하는 방법이다.
// 실무에서는 주로 정형화된 컨트롤러, 서비스, 리포지토리 같은 코드는 컴포넌트 스캔을 사용하고,
// ☆ 정형화 되지 않거나, 상황에 따라서 구현 클래스를 변경해야 하면 설정을 통해 스프링 빈으로 등록한다. ☆
public class SpringConfig {

    // 스프링이 configuration을 읽으면서 스프링 빈에 등록해준다.
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
