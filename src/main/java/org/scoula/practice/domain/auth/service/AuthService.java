package org.scoula.practice.domain.auth.service;

import jakarta.websocket.Session;
import lombok.RequiredArgsConstructor;
import org.scoula.practice.domain.auth.component.SessionManager;
import org.scoula.practice.domain.auth.controller.dto.LoginRequest;
import org.scoula.practice.domain.auth.controller.dto.LoginResponse;
import org.scoula.practice.domain.member.entity.MemberEntity;
import org.scoula.practice.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final SessionManager sessionManager;
    private final MemberRepository memberRepository;

    public LoginResponse login(LoginRequest request) {
        MemberEntity member = memberRepository.findByLoginId(request.loginId())
                .orElseThrow(() -> new RuntimeException("아이디가 올바르지 않습니다."));

        if(member.isValidPassword(request.password())) {
            String token = sessionManager.createSession(member.getId());
            return new LoginResponse(token, "BEARER");
        }
        throw new RuntimeException("비밀번호가 올바르지 않습니다.");
    }
}
