package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/* import에 static 을 붙이는 이유? static 을 사용하지 않는다면 매번 클래스이름.함수 방식으로 사용하여야 하므로
    static 을 사용한다면 메서드를 바로 사용하도록 하여 가독성을 높일 수 있다!
 */
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

// shift + F10 및 control + R 입력 시, 이전 수행했던 테스트 다시 수행.
class MemberServiceTest {

    MemberService memberService;
    /* 현재, MemberService 에서 MemoryMemberRepository 객체를 생성하고, 테스트에서도 객체를 생성하고 있어, 서로 다른 인스턴스이다. 즉, 내용이 달라질 리스크가 존재하기 때문에,
    하나의 인스턴스로 관리하는 것이 좋다. MemoryMemberRepository 필드가 static이 아닌경우 문제가 생길 가능성이 있다.
    */
    MemoryMemberRepository memberRepository;

    // 테스트 수행전 실행이되는 메소드
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        // memberService의 생성자를 통한 의존성 주입. Dependency Injection. memberRepository를 따로 생성하는 것이 아닌 외부에서 넣어주는 방식.
        // 하나의 memberRepository 인스턴스로 관리가 가능하다.
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }


    @Test
    void 회원가입() {
        /*
            중요한 테스트 사항 !
        */
        // given. 어떠한 상황이 주어졌다면
        Member member = new Member();
        member.setName("spring");

        // when. 어떤것을 실행하였을 때,
        Long saveId = memberService.join(member);

        // then. 이러한 결과가 나와야한다.
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    // 조인의 핵심은 중복회원 검증이 되어야 한다!
    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        // assertThrows 를 통한 lambda 예외처리 방식. asserThrows는 해당 예외를 반환하게 되어있음.
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        /*
        // try-catch를 사용한 예외처리
        try {
            memberService.join(member2);
            fail("예외가 발생해야 합니다.");
        } catch (IllegalStateException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.1`2");
        }
        */


        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}