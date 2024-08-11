package io.github.aikusonitradesystem.authserver.session.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginDto {
    private String loginState;
}
