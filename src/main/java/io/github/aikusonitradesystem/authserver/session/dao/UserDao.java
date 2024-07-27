package io.github.aikusonitradesystem.authserver.session.dao;

import io.github.aikusonitradesystem.authserver.session.constants.SessionAuthServerErrorCode;
import io.github.aikusonitradesystem.authserver.session.model.dto.UserDto;
import io.github.aikusonitradesystem.authserver.session.model.dto.UserSearchDto;
import io.github.aikusonitradesystem.authserver.session.model.form.UserSearchForm;
import io.github.aikusonitradesystem.authserver.session.model.mapper.UserModelMapper;
import io.github.aikusonitradesystem.authserver.session.repository.UserRepository;
import io.github.aikusonitradesystem.core.exception.ATSRuntimeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserDao {
    private final UserRepository userRepository;
    private final UserModelMapper userModelMapper;

    @Transactional(readOnly = true)
    public UserDto getUser(String username) {
        return userModelMapper.toUserDto(userRepository.findByUsername(username));
    }

    @Transactional(readOnly = true)
    public List<UserDto> getUserList(UserSearchDto userSearchDto) {
        return userModelMapper.toUserDtoList(userRepository.findAll());
    }

    @Transactional
    public UserDto insertUser(UserDto userDto) {
        if (userRepository.findByUsername(userDto.getUsername()) != null) {
            throw new ATSRuntimeException(SessionAuthServerErrorCode.USER_ALREADY_EXISTS, "USD-000001", "이미 존재하는 사용자입니다.");
        }
        return userModelMapper.toUserDto(userRepository.save(userModelMapper.toUserEntity(userDto)));
    }

    @Transactional
    public UserDto saveUser(UserDto userDto) {
        return userModelMapper.toUserDto(userRepository.save(userModelMapper.toUserEntity(userDto)));
    }

    @Transactional
    public UserDto deleteUser(String username) {
        return userModelMapper.toUserDto(userRepository.deleteByUsername(username));
    }
}
