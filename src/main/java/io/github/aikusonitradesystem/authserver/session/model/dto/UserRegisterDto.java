package io.github.aikusonitradesystem.authserver.session.model.dto;

import lombok.Data;

@Data
public class UserRegisterDto {
    private String username;
    private String password;
}
