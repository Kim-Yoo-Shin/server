package server.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.server.domain.Member;
import server.server.payload.ApiResponse;
import server.server.payload.SignInRequest;
import server.server.service.MemberService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
@Slf4j
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping("/member")
    public ResponseEntity<?> create(@RequestBody MemberForm memberForm){
        Member member = new Member(memberForm.getUserId(),memberForm.getName(),memberForm.getPassward());
        String joinMsg = memberService.join(member);
        if (joinMsg.equals("Can not join cause by duplicate userId.")) {
            return new ResponseEntity(new ApiResponse(false, "User id has already exist!"),
                    HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(member);
    }

    /**
     * 로그인
     **/
    @PostMapping("/signin")
    public void signIn (@Valid @RequestBody SignInRequest signInRequest) {

    }


}
