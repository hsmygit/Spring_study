package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
/*이 애노티네이션이 있으면 이 Member서비스를 스프링이 컨테이너에 등록해줌.*/
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired //서비스를 생성할 때 스프링이 컨테이너에 있는 레포지토리를 넣어줌.-> di
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    //Test에서 같은 repository를 test하기 위함.memberRepository 인스턴스를 외부(test)에서 받아 오도록
    //이런거를 dependency injection이라 한다. -> di
    /**회원가입 (같은 이름의 중복회원 X)
     * memberrepository에서 save만 호출하면 된다.**/
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId(); //임의로 ID를 반환하도록 설정.
    }
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { // optional안에 null이 아니라 값이 존재하면 동작.(optional 메소드)
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                }); //throw는 예외를 발생시키는 데 사용되는 키워드.
    }

    /**전체 회원 조회**/
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
    /**회원 업데이트**/

    /**회원 삭제**/

