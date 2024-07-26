package io.github.aikusonitradesystem.authserver.session.controller;

import io.github.aikusonitradesystem.core.constants.ErrorCode;
import io.github.aikusonitradesystem.mvcstandard.model.view.ATSResponseBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    @GetMapping("/validate")
    public ResponseEntity<ATSResponseBody<Void>> auth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            return ATSResponseBody.<Void>ok(null)
                    .toResponseEntity();
        }

        return ATSResponseBody.<Void>error(ErrorCode.UNAUTHORIZED, "ATC000001", "미인증 상태입니다.")
                .toResponseEntity();
    }

    @GetMapping("/login-success")
    public ResponseEntity<ATSResponseBody<String>> loginSuccess() {
        return ATSResponseBody.<String>ok("OK")
                .toResponseEntity();
    }

    @GetMapping("/logout-success")
    public ResponseEntity<ATSResponseBody<String>> logoutSuccess() {
        return ATSResponseBody.<String>ok("OK")
                .toResponseEntity();
    }

    @GetMapping("/session-expired")
    public ResponseEntity<ATSResponseBody<String>> sessionExpired() {
        return ATSResponseBody.<String>ok("OK")
                .toResponseEntity();
    }
}
