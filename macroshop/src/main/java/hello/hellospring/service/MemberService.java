package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


//@Service    // 해당 애너테이션이 있어야 스프링 컨테이너에 등록이 된다.
// command + shift + t or ctrl + shift + t . 테스트 코드 생성 단축키
public class MemberService {

    // 생성자를 통한 의존성 주입 방식으로 구현하여, 하나의 인스턴스로 관리하도록 한다.
    // 즉, Test와 Service간에 동일한 레파지토리를 사용하여야 한다.
    private final MemberRepository memberRepository;

//    @Autowired
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    // 자바 코드로 직접 빈에 등록하는 방법?
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
            회원 가입
         */
    public Long join(Member member) {
        // 같은 이름의 중복 회원 X
        // command + option + v or ctrl + alt + v 입력시 표현식 optinal 자동완성!! <<중요>>
        // ctrl + t 선택하여 extract method 선택 및 ctrl(cmd) + alt(option) + m. 메소드 추출 단축키. findByName 하여 뭔가 찾는 로직을 메소드로 빼면 좋다.
        validateDuplicateMember(member);    // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> result = memberRepository.findByName(member.getName());
        if (result.isPresent()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /*
       전체 회원 조회
     */
    // 서비스는 비즈니스 로직다운 이름, 로직을 사용해야한다.
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
