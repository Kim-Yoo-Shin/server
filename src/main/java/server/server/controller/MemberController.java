package server.server.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import server.server.domain.Member;
import server.server.memberDto.ApiResponse;
import server.server.memberDto.MemberForm;
import server.server.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    @Autowired
    private final MemberService memberService;

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @ResponseBody
    @PatchMapping("/user")
    public ResponseEntity<?> patchUserInfo(@RequestBody MemberForm modifiedInfo, HttpServletRequest request){
        String currentUserId = memberService.getCurrentUserInfo(request).get().getUserId();
        boolean patchSuccess = memberService.patchInfo(currentUserId, modifiedInfo);

        if (!patchSuccess) {
            return new ResponseEntity(new ApiResponse(false,"Not exist user!"),
                    HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(modifiedInfo);
    }

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @ResponseBody
    @GetMapping("/user/info")
    public Optional<Member> currentUserInfo(HttpServletRequest request){
        return memberService.getCurrentUserInfo(request);
    }

}
