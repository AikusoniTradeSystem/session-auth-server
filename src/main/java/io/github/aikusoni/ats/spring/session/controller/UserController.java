package io.github.aikusoni.ats.spring.session.controller;

import io.github.aikusoni.ats.spring.session.model.dto.UserDto;
import io.github.aikusoni.ats.spring.session.model.form.UserForm;
import io.github.aikusoni.ats.spring.session.model.form.UserRegisterForm;
import io.github.aikusoni.ats.spring.session.model.form.UserSearchForm;
import io.github.aikusoni.ats.spring.session.model.mapper.UserModelMapper;
import io.github.aikusoni.ats.spring.session.service.UserService;
import io.github.aikusoni.ats.spring.mvcstandard.model.view.ATSResponseBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static io.github.aikusoni.ats.spring.session.constants.SessionMessageCode.*;

@Slf4j
@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserModelMapper userModelMapper;

    @GetMapping("/username/{username}")
    public ResponseEntity<ATSResponseBody<UserDto>> getUser(
            @PathVariable String username
    ) {
        UserDto userDto = userService.getUser(username);
        return ATSResponseBody.<UserDto>ok(userDto)
                .toResponseEntity();
    }

    @GetMapping
    public ResponseEntity<ATSResponseBody<List<UserDto>>> getUsers(
            UserSearchForm searchForm
    ) throws Exception {
        List<UserDto> userDtoList = userService.getUserList(userModelMapper.toUserSearchDto(searchForm));
        return ATSResponseBody.<List<UserDto>>ok(userDtoList)
                .toResponseEntity();
    }

    @PostMapping("/register")
    public ResponseEntity<ATSResponseBody<UserDto>> register(
            @Validated @RequestBody UserRegisterForm userRegisterForm
    ) throws Exception {
        return ATSResponseBody.<UserDto>ok(userService.register(userModelMapper.toUserDto(userRegisterForm)), USER_REGISTER_SUCCESS)
                .toResponseEntity();
    }

    @PutMapping("/update")
    public ResponseEntity<ATSResponseBody<UserDto>> update(
            @Validated @RequestBody UserForm userForm
    ) throws Exception {
        return ATSResponseBody.<UserDto>ok(userService.update(userModelMapper.toUserDto(userForm)), USER_UPDATE_SUCCESS)
                .toResponseEntity();
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<ATSResponseBody<UserDto>> delete(
            @PathVariable String username
    ) throws Exception {
        return ATSResponseBody.<UserDto>ok(userService.delete(username), USER_DELETE_SUCCESS)
                .toResponseEntity();
    }
}
