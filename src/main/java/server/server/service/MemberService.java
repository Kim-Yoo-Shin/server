package server.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.server.domain.Member;
import server.server.repository.MemberRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true) // 읽기 전용 메서드 성능 최적화
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     **/
    @Transactional
    public String join(Member member){
        if(memberRepository.existsByUserId(member.getUserId())){
            return "Can not join cause by duplicate userId.";
        }
        member.setDatetime(LocalDateTime.now());
        memberRepository.save(member);
        return member.getUserId().toString();
    }

    /**
     * 전체 회원 조회
     **/
    public List<Member> findAll(){
        return memberRepository.findAll();
    }




}
