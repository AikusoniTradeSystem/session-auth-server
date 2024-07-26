package io.github.aikusonitradesystem.authserver.session.service;

import io.github.aikusonitradesystem.authserver.session.dao.UserDao;
import io.github.aikusonitradesystem.authserver.session.model.form.UserRegisterForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;

    public String registerUser(UserRegisterForm userRegisterForm) throws Exception {
        return null;
    }
}
