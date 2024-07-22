package io.github.aikusonitradesystem.authserver.session.controller;

import io.github.aikusonitradesystem.mvcstandard.model.view.ATSResponseBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    @GetMapping
    public ResponseEntity<ATSResponseBody<String>> test(
            @RequestParam(value = "text") String text
    ) throws Exception {
        log.info("text : " + text);
        return ATSResponseBody.ok(text).toResponseEntity();
    }

}
