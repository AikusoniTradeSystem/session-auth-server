package io.github.aikusonitradesystem.authserver.session.helper;

import io.github.aikusonitradesystem.authserver.session.properties.AuthServerProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PasswordHelper {
    private final AuthServerProperties authServerProperties;

    public String encode(PasswordEncoder passwordEncoder, String password) {
        return "{%s}%s".formatted(authServerProperties.getPasswordEncoderType(), passwordEncoder.encode(password));
    }

    public String passwordType(String encodedPassword) {
        return encodedPassword.substring(1, encodedPassword.indexOf("}"));
    }

    public String encodedPassword(String encodedPassword) {
        return encodedPassword.substring(encodedPassword.indexOf("}") + 1);
    }
}
