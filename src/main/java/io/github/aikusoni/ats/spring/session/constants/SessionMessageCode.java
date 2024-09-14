package io.github.aikusoni.ats.spring.session.constants;

import io.github.aikusoni.ats.spring.core.common.MessageCode;

import static io.github.aikusoni.ats.spring.core.common.MessageCode.of;

public interface SessionMessageCode {
    MessageCode INVALID_AUTHENTICATION = of("session.invalid_authentication");
    MessageCode NEED_LOGIN_AUTH = of("session.need_login_auth");
    MessageCode PASSWORD_IS_TOO_OLD = of("session.password_is_too_old");
    MessageCode USER_ALREADY_EXISTS = of("session.user_already_exists");
    MessageCode USER_DELETE_SUCCESS = of("session.user_delete_success");
    MessageCode USER_NOT_FOUND = of("session.user_not_found");
    MessageCode USER_REGISTER_SUCCESS = of("session.user_register_success");
    MessageCode USER_UPDATE_SUCCESS = of("session.user_update_success");
}
