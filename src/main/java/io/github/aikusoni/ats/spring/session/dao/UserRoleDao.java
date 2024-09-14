package io.github.aikusoni.ats.spring.session.dao;

import io.github.aikusoni.ats.spring.session.model.entity.UserRoleEntity;
import io.github.aikusoni.ats.spring.session.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRoleDao {
    private final UserRoleRepository userRoleRepository;

    public List<String> getRoles(String username) {
        return userRoleRepository.findAllByUsername(username).stream().map(UserRoleEntity::getRolename).toList();
    }
}
