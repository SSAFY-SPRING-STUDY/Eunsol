package org.scoula.practice.domain.auth.component;

import org.springframework.stereotype.Controller;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class SessionManager {
    private final Map<String, Long> sessionStore = new ConcurrentHashMap<>();

    public String createSession(Long id) {
        String token = UUID.randomUUID().toString();
        sessionStore.put(token, id);
        return token;
    }

    public void removeSession(String accessToken) {
        sessionStore.remove(accessToken);
    }

    public Optional<Long> getMemberId(String accessToken) {
        return Optional.ofNullable(sessionStore.get(accessToken));
    }
}
