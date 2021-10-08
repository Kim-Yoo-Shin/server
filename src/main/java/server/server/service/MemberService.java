package server.server.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.server.config.security.JwtAuthenticationFilter;
import server.server.config.security.JwtTokenProvider;
import server.server.domain.Member;
import server.server.memberDto.MemberForm;
import server.server.memberDto.UserVo;
import server.server.repository.MemberRepository;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true) // 읽기 전용 메서드 성능 최적화
@AllArgsConstructor
public class MemberService {

    @Autowired
    private final MemberRepository memberRepository;
    @Autowired
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    private final JwtTokenProvider jwtTokenProvider;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입
     **/
    @Transactional
    public String join(Member member){
        memberRepository.save(member);
        return member.getUserId().toString();
    }

    /**
     * 전체 회원 조회
     **/
    public List<Member> findAll(){
        return memberRepository.findAll();
    }


    /**
     * 로그인
     **/
    @Transactional
    public Boolean login(UserVo userVo){
        Optional<Member> targetId = memberRepository.findByUserId(userVo.getUserId());
        if(targetId.isEmpty()) return false;

        else return false;
    }

    /**
     * 회원 정보 수정
     **/
    @Transactional
    public boolean patchInfo(String userId, MemberForm modifiedInfo){
        Optional<Member> optMember = memberRepository.findByUserId(userId);
        Member member = optMember.get();

        if (member == null) {
            return false;
        }

        if (modifiedInfo.getPassword() != null) {
            member.setPassword(passwordEncoder.encode(modifiedInfo.getPassword()));
        }
        if (modifiedInfo.getUserName() != null) {
            member.setName(modifiedInfo.getUserName());
        }

        memberRepository.save(member);
        return true;
    }

    @Secured({"ROLE_USER","ROLE_MANA"})
    public Optional<Member> getCurrentUserInfo(HttpServletRequest request){
        String token = jwtAuthenticationFilter.getJwtFromRequest(request);
        Long currentUserId = jwtTokenProvider.getUserIdFromJWT(token);
        return memberRepository.findById(currentUserId);
    }
}
