package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository//스프링이 이 repository를 컨테이너에 등록.
public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();
    //key : 회원 ID (Long타입), value : Member(도메인 객체)
    private static long sequence = 0L;//key값 생성, 정수+L을 하면 정수를 int가 아니라 long타입으러 저장.
    /**
     * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
     */
    @Override
    public Member save(Member member) {
        member.setId(++sequence);//key 값
        store.put(member.getId(), member);//HashMap에 Member객체 추가 (store.put)
        return member;
    }//store의 key값과 member객체의 id의 값이 같아짐 -> key로 member객체를 식벽가능.
    @Override //key값으로 value가져오기.
    public Optional<Member> findById(Long id) {
        //HashMap에서 Member객체 가져오기.(store.get)
        return Optional.ofNullable(store.get(id));
        /*그냥 return store.get(id)를 하면 데이터가 없을 때 null을 그냥 반환한다.
         하지만, 요즘에는 null이 나올 가능성이 있으면 optional이라는 것을 감싼다.
          이렇게 갑사써 반환을 해주면 클라이언트에서 추가 작업을 할 수 있다.*/
    }
    @Override //value 값으로 Member객체 가져오기.
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //모든 member가 반환.
    }//위에는  Map이지만 여기선 List사용 (실무에서 많이 사용.)

    public void clearStore() {
        store.clear();
    }//원활한 Test를 위해 store를 비우는 함수를 만들어 줌.
}