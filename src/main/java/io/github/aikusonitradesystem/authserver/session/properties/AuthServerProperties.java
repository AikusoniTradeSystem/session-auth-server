package io.github.aikusonitradesystem.authserver.session.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "ats-session-auth")
public class AuthServerProperties {
    private String passwordEncoderType;
    private Boolean allowSwaggerWithoutLogin;
    private List<String> corsAllowedOrigins;
}
