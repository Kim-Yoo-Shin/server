package server.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.server.domain.Member;
import server.server.repository.MemberRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true) // 읽기 전용 메서드 성능 최적화
@RequiredArgsConstructor
public class MeberService {

    private final MemberRepository memberRepository;

    // 회원 가입
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);

        member.setCreateDate(new Date());

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> findMembers = memberRepository.findById(member.getId());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    public List<Member> findAll(){
        return memberRepository.findAll();
    }
    // 특정 회원 조회
    // 차후 getById를 통한 테이블 오류가 없다면 변경하기
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
