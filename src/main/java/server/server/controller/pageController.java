package server.server.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.server.memberDto.ApiResponse;
import server.server.service.MemberService;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/page")
@RequiredArgsConstructor
@Slf4j
public class pageController {
    @Autowired
    private final MemberService memberService;

    @GetMapping("/user")
    public String getCurrentUserName(HttpServletRequest request){
        return memberService.getCurrentUserInfo(request).get().getName();
    }
}
