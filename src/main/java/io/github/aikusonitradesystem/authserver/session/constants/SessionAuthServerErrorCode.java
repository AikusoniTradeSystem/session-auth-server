package io.github.aikusonitradesystem.authserver.session.constants;

import io.github.aikusonitradesystem.core.common.BaseErrorCode;
import lombok.Getter;

@Getter
public enum SessionAuthServerErrorCode implements BaseErrorCode {
    CUSTOM_ERROR_CODE(10000, 500),
    FAILED_TO_FIND_USER(10401, 401),
    PASSWORD_IS_TOO_OLD(11401, 401),
    USER_ALREADY_EXISTS(10409, 409),
    ;

    final int errorCode;
    final int statusCode;

    SessionAuthServerErrorCode(int errorCode, int statusCode) {
        this.errorCode = errorCode;
        this.statusCode = statusCode;
    }
}
