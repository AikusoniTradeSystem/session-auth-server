package io.github.aikusonitradesystem.authserver.session.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Data;

@Data
@IdClass(UserRoleEntityId.class)
@Entity(name = "USER_ROLE")
public class UserRoleEntity {
    @Id
    private String username;

    @Id
    private String rolename;
}
