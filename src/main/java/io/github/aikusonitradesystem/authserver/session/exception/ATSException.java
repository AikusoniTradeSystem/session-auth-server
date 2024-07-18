package io.github.aikusonitradesystem.authserver.session.exception;

import io.github.aikusonitradesystem.authserver.session.constants.ErrorCode;
import lombok.Getter;

@Getter
public class ATSException extends Exception {
    private final ErrorCode errorCode;
    private final String errorAlias;

    public ATSException(ErrorCode errorCode, String errorAlias, String message) {
        super(message);
        this.errorCode = errorCode;
        this.errorAlias = errorAlias;
    }
}
