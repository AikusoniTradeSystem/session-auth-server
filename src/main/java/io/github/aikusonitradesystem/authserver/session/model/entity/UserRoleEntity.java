package io.github.aikusonitradesystem.authserver.session.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "USERROLE")
public class UserRoleEntity {
    @Id
    private String username;

    @Id
    private String rolename;
}
