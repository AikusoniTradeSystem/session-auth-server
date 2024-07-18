package io.github.aikusonitradesystem.authserver.session.exception;

import io.github.aikusonitradesystem.authserver.session.constants.ErrorCode;
import lombok.Getter;

@Getter
public class ATSRuntimeException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String errorAlias;

    public ATSRuntimeException(ErrorCode errorCode, String errorAlias, String message) {
        super(message);
        this.errorCode = errorCode;
        this.errorAlias = errorAlias;
    }
}
