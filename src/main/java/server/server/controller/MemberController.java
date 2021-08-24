package server.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.server.domain.Member;
import server.server.service.MemberService;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/member")
    public List<Member> memberList() {
        log.info("{}" ,memberService.findAll().toString());
        System.out.println(memberService.findAll().toString());
        return memberService.findAll();
    }

    @PostMapping("/member")
    public String create(@RequestBody Member member){
        return memberService.join(member).toString();
    }

}
