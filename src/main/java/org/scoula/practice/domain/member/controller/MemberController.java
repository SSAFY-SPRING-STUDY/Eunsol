package org.scoula.practice.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.scoula.practice.domain.auth.service.AuthService;
import org.scoula.practice.domain.auth.util.AuthorizationUtils;
import org.scoula.practice.domain.member.controller.dto.MemberRequest;
import org.scoula.practice.domain.member.controller.dto.MemberResponse;
import org.scoula.practice.domain.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;
    private final AuthService authService;

    @PostMapping()
    public ResponseEntity<MemberResponse> join(@RequestBody MemberRequest request){
        MemberResponse response = memberService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/me")
    public ResponseEntity<MemberResponse> me(@RequestHeader("Authorization") String authHeader){
        MemberResponse response = null;
        try{
            String accessToken = AuthorizationUtils.getAccessToken(authHeader);
            Long memberId = authService.getMemberId(accessToken);
            response = memberService.findById(memberId);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(response);
    }
}
