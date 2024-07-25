package io.github.aikusonitradesystem.authserver.session.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "USERS")
public class UserEntity {
    @Id
    private String username;

    private String password;
}
