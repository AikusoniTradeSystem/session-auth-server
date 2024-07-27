package io.github.aikusonitradesystem.authserver.session.service;

import io.github.aikusonitradesystem.authserver.session.dao.UserDao;
import io.github.aikusonitradesystem.authserver.session.helper.PasswordHelper;
import io.github.aikusonitradesystem.authserver.session.model.dto.UserDto;
import io.github.aikusonitradesystem.authserver.session.model.dto.UserRegisterDto;
import io.github.aikusonitradesystem.authserver.session.model.dto.UserSearchDto;
import io.github.aikusonitradesystem.authserver.session.model.form.UserForm;
import io.github.aikusonitradesystem.authserver.session.model.form.UserRegisterForm;
import io.github.aikusonitradesystem.authserver.session.model.form.UserSearchForm;
import io.github.aikusonitradesystem.authserver.session.model.mapper.UserModelMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;
    private final PasswordHelper passwordHelper;
    private final PasswordEncoder passwordEncoder;

    public UserDto getUser(String username) {
        return userDao.getUser(username);
    }

    public List<UserDto> getUserList(UserSearchDto userSearchDto) {
        return userDao.getUserList(userSearchDto);
    }

    public UserDto register(UserDto userDto) throws Exception {
        userDto.setPassword(passwordHelper.encode(passwordEncoder, userDto.getPassword()));
        return userDao.insertUser(userDto);
    }

    public UserDto update(UserDto userDto) throws Exception {
        Optional.ofNullable(userDto)
                .map(UserDto::getPassword)
                .map(rawPassword -> passwordHelper.encode(passwordEncoder, rawPassword))
                .ifPresent(userDto::setPassword);
        return userDao.saveUser(userDto);
    }

    public UserDto delete(String username) throws Exception {
        return userDao.deleteUser(username);
    }
}
