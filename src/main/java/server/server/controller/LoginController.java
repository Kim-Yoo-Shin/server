package server.server.controller;


import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import server.server.config.security.JwtTokenProvider;
import server.server.domain.Member;
import server.server.domain.RoleDao;
import server.server.domain.RoleName;
import server.server.exception.AppException;
import server.server.memberDto.ApiResponse;
import server.server.memberDto.JwtAuthenticationResponse;
import server.server.memberDto.MemberForm;
import server.server.memberDto.UserVo;
import server.server.repository.MemberRepository;
import server.server.repository.RoleRepository;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Collections;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class LoginController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signup")
    public ResponseEntity<?> registerMember(@Valid @RequestBody MemberForm memberForm) {
        if(memberRepository.existsByUserId(memberForm.getUserId())){
            return new ResponseEntity(new ApiResponse(false, "User id has already exist!"),
                    HttpStatus.BAD_REQUEST);
        }
        Member member = new Member(memberForm.getUserId(), memberForm.getName(),
                passwordEncoder.encode(memberForm.getPassward()), LocalDateTime.now());

//        RoleDao roleDao = new RoleDao(RoleName.ROLE_USER);
//        roleRepository.save(roleDao);
        RoleDao userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set"));

        member.setRoles(Collections.singleton(userRole));
        memberRepository.save(member);

        return ResponseEntity.ok(member);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateMember(@Valid @RequestBody UserVo userVo) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userVo.getUserId(), userVo.getPassword()));


        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }
}
