package io.github.aikusonitradesystem.authserver.session.model.form;

import lombok.Data;

@Data
public class UserRegisterForm {
    private String username;
    private String password;
}
