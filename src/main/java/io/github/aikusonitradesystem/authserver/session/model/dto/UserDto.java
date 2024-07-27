package io.github.aikusonitradesystem.authserver.session.model.dto;

import io.github.aikusonitradesystem.authserver.session.annotation.HideSensitiveData;
import lombok.Data;

@Data
public class UserDto {
    private String username;
    @HideSensitiveData
    private String password;
}
