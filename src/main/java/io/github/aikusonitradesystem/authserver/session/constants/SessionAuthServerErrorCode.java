package io.github.aikusonitradesystem.authserver.session.constants;

import io.github.aikusonitradesystem.core.common.BaseErrorCode;
import lombok.Getter;

@Getter
public enum SessionAuthServerErrorCode implements BaseErrorCode {
    CUSTOM_ERROR_CODE(10000, 500)
    ;

    final int errorCode;
    final int statusCode;

    SessionAuthServerErrorCode(int errorCode, int statusCode) {
        this.errorCode = errorCode;
        this.statusCode = statusCode;
    }
}
