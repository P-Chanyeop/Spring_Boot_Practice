package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);

        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // store.get(id)가 null 일 가능성이 있어 Optional로 감싸주어야 한다.
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // store를 쭉 돌면서, 매개변수로 들어온 name 과 같은것만 필터링 하여 찾는다. 찾으면 return
        // 결과는 Optional로 반환이 된다.
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        // store은 HashMap이고, 반환의 형태는 List이기 때문에, 리스트로 반환.
        // Member들이 반환된다.
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
