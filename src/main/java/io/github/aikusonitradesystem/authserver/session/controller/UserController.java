package io.github.aikusonitradesystem.authserver.session.controller;

import io.github.aikusonitradesystem.authserver.session.model.form.UserRegisterForm;
import io.github.aikusonitradesystem.authserver.session.service.UserService;
import io.github.aikusonitradesystem.mvcstandard.model.view.ATSResponseBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ATSResponseBody<String>> register(
            @Validated @RequestBody UserRegisterForm userRegisterForm
    ) throws Exception {
        return ATSResponseBody.<String>ok("유저 생성에 성공했습니다.")
                .toResponseEntity();
    }
}
