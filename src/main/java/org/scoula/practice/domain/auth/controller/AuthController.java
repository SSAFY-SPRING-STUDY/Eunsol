package org.scoula.practice.domain.auth.controller;

import lombok.RequiredArgsConstructor;
import org.scoula.practice.domain.auth.controller.dto.LoginRequest;
import org.scoula.practice.domain.auth.controller.dto.LoginResponse;
import org.scoula.practice.domain.auth.service.AuthService;
import org.scoula.practice.domain.auth.util.AuthorizationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        LoginResponse response = null;
        try{
            response = authService.login(request);
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String authHeader){
        //authHeader = "token" X
        //authHeader = "BEARER sdkfjksjdjfksjkfdfsjkfjs" -> 토큰 값 앞에 BEARER(토큰 타입) 같이 옴 => 파싱 필요
        String accessToken = AuthorizationUtils.getAccessToken(authHeader);
        authService.logout(accessToken);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
