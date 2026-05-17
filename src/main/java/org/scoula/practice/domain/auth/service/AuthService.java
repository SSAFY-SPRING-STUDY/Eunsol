package org.scoula.practice.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.scoula.practice.domain.auth.component.SessionManager;
import org.scoula.practice.domain.auth.controller.dto.LoginRequest;
import org.scoula.practice.domain.auth.controller.dto.LoginResponse;
import org.scoula.practice.domain.member.entity.MemberEntity;
import org.scoula.practice.domain.member.repository.MemberRepository;
import org.scoula.practice.global.exception.CustomException;
import org.scoula.practice.global.exception.error.ErrorCode;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final SessionManager sessionManager;
    private final MemberRepository memberRepository;

    public LoginResponse login(LoginRequest request) {
        MemberEntity member = memberRepository.findByLoginId(request.loginId())
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_USERNAME));

        if(member.isValidPassword(request.password())) {
            String token = sessionManager.createSession(member.getId());
            return new LoginResponse(token, "Bearer");
        }
        throw new CustomException(ErrorCode.INVALID_PASSWORD);
    }

    public void logout(String accessToken) {
        sessionManager.removeSession(accessToken);
    }

    public Long getMemberId(String accessToken) {
        return sessionManager.getMemberId(accessToken).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
    }
}
