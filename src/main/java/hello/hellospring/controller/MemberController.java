package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    /*이 애노테이션이 있으면 스프링 컨테이너가 뜰 때 MemberContoller를 생성을 하고 이때 생성자를 호출한 뒤에
    이때, 스프링이 컨테이너에 있는 memberService를 컨트롤러와 연결을 시켜 줌.
    즉,스프링이 스프링빈에 등록되어 있는 서비스객체를 가져와 준다. -> 이게 di이다.*/
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new") //url에 치는 것
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new") // post방식으로 넘어 온 것.
    public String create(MemberForm form) { //MemberForm의 name에 값이 들어감.(html로 부터 넘어온 name가 들어간다
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/"; // 홈 화면으로 되돌아 가기.
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
