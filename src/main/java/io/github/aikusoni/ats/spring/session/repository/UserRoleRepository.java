package io.github.aikusoni.ats.spring.session.repository;

import io.github.aikusoni.ats.spring.session.model.entity.UserRoleEntity;
import io.github.aikusoni.ats.spring.session.model.entity.UserRoleEntityId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, UserRoleEntityId> {
    List<UserRoleEntity> findAllByUsername(String username);
}
