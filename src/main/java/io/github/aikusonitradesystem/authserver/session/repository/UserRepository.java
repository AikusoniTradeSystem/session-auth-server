package io.github.aikusonitradesystem.authserver.session.repository;

import io.github.aikusonitradesystem.authserver.session.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByUsername(String username);
    UserEntity deleteByUsername(String username);
}
