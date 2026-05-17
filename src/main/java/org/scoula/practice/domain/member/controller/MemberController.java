package org.scoula.practice.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.scoula.practice.domain.member.controller.dto.MemberRequest;
import org.scoula.practice.domain.member.controller.dto.MemberResponse;
import org.scoula.practice.domain.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping()
    public ResponseEntity<MemberResponse> join(@RequestBody MemberRequest request){
        MemberResponse response = memberService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
