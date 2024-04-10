package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;
class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();
    @AfterEach //Test가 끝날 때 마다 아래 함수를 실행하도록 한다.
    public void afterEach() {
        repository.clearStore();
    }
    /* Test를 할 때 실행 순서는 보장을 안해준다. 따라서 Test가 하나 끝나고 나면
    * 데이터를 클리어 해줘야 findByName와 같은 Test에서 오류가 발생하지 않는다.
    * findAll Test가 먼저 실행되면, findByName에서 findAll의 spring1,2를 가져와 버린다.
    * 그러면 key값이 다르기 때문에 Test가 실팽하게 된다.*/

    @Test  //save를 test할 수 있다.
    public void save() {
        //저장할 객체 생성
        Member member = new Member();
        member.setName("spring");
        // repository에 생성한 객체를 저장.
        repository.save(member);
        // 스스로 검증 : findById로 제대로 저장 되었는지 확인.
        Member result = repository.findById(member.getId()).get();
        //.get으로 optional을 까서 바로 가져올 수 있다.(test에서만 사용 권장.)
        assertThat(member).isEqualTo(result);
        //assert기능으로 member가 result랑 같은지 검증.
    }
    @Test //findByName Test
    public void findByName() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        //when
        Member result1 = repository.findByName("spring1").get();
        Member result2 = repository.findByName("spring2").get();
        //then
        assertThat(result1).isEqualTo(member1);
        assertThat(result2).isEqualTo(member2);
    }
    @Test //findAll Test
    public void findAll() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        //when
        List<Member> result = repository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
    }
}