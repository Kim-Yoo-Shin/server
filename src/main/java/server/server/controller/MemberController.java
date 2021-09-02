package server.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.server.domain.Member;
import server.server.memberDto.ApiResponse;
import server.server.memberDto.MemberForm;
import server.server.service.MemberService;

import javax.servlet.http.HttpSession;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
@Slf4j
public class MemberController {
    @Autowired
    private MemberService memberService;



}
