package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import java.util.List;
import java.util.Optional;
public interface MemberRepository {
    Member save(Member member); //회원을 저장하면 저장된 회원을 반환, 저장소에 회원을 저장.
    Optional<Member> findById(Long id); //ID로 회원을 찾는다.
    Optional<Member> findByName(String name); //name로 회원을 찾는다.
    List<Member> findAll();//지금 까지 저장된 모든 회원 list를 반환.
}
/*참고
 * Optional이란?
 * 가져오는 데이터가 null인 경우 optional을 감싸서 클라이언트로 보낼 수 있다.
 * 그럼 클라이언트에서 추가 작업이 가능.
 */