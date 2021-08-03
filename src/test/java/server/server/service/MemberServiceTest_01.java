package server.server.service;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import server.server.domain.Member;
import server.server.repository.MemberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest_01 {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("lee");

        //when
        Long saveId = memberService.join(member);

        //then
        Assertions.assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test
    public void 중복회원예외() throws Exception{
        //given
        Member member = new Member();
        member.setName("lee");
        Member member1 = new Member();
        member1.setName("lee");

        //when
        memberService.join(member);
        try{
            memberService.join(member1);
        }catch (IllegalStateException e){
            return;
        }
        //then
        Assertions.fail("예외가 발생해야한다.");

    }
}
