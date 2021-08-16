package server.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.server.domain.Member;
import server.server.service.MemberService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
@Slf4j
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/member")
    public List<Member> memberList() {
        System.out.println(memberService.findAll().toString());
        return memberService.findAll();
    }

    @PostMapping("/member")
    public Long create(@RequestBody MemberForm memberForm){
        Member member = new Member();
        member.setName(memberForm.getName());
        member.setPassword(memberForm.getPassward());
        return memberService.join(member);
    }

}
