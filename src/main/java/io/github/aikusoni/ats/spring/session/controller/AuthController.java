package io.github.aikusoni.ats.spring.session.controller;

import io.github.aikusoni.ats.spring.session.constants.SessionMessageCode;
import io.github.aikusoni.ats.spring.session.model.dto.LoginDto;
import io.github.aikusoni.ats.spring.core.constants.ErrorCode;
import io.github.aikusoni.ats.spring.mvcstandard.model.view.ATSResponseBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    @GetMapping("/get-role")
    public ResponseEntity<ATSResponseBody<Void>> auth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            if (authentication.getPrincipal() instanceof User user) {
                Map<String, String> headers = new HashMap<>();
                headers.put("X-User", user.getUsername());
                headers.put("X-Roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(",")));
                return ATSResponseBody.<Void>ok(null)
                        .headers(headers)
                        .toResponseEntity();
            } else {
                return ATSResponseBody.<Void>error(ErrorCode.UNAUTHORIZED, "ATC-000001", SessionMessageCode.INVALID_AUTHENTICATION)
                        .toResponseEntity();
            }
        }

        return ATSResponseBody.<Void>ok(null)
                .headers(Map.of("X-User", "x"))
                .toResponseEntity();
    }

    @GetMapping("/login-success")
    public ResponseEntity<ATSResponseBody<LoginDto>> loginSuccess() {
        return ATSResponseBody.<LoginDto>ok(LoginDto.builder().loginState("LOGIN").build())
                .toResponseEntity();
    }

    @GetMapping("/logout-success")
    public ResponseEntity<ATSResponseBody<LoginDto>> logoutSuccess() {
        return ATSResponseBody.<LoginDto>ok(LoginDto.builder().loginState("LOGOUT").build())
                .toResponseEntity();
    }

    @GetMapping("/session-expired")
    public ResponseEntity<ATSResponseBody<LoginDto>> sessionExpired() {
        return ATSResponseBody.<LoginDto>ok(LoginDto.builder().loginState("SESSION_EXPIRED").build())
                .toResponseEntity();
    }
}
