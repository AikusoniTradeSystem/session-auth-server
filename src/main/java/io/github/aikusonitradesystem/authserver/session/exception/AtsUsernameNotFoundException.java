package io.github.aikusonitradesystem.authserver.session.exception;

import io.github.aikusonitradesystem.core.common.BaseErrorCode;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AtsUsernameNotFoundException extends UsernameNotFoundException {
    private final BaseErrorCode errorCode;
    private final String errorAlias;

    public AtsUsernameNotFoundException(BaseErrorCode errorCode, String errorAlias, String message) {
        super(message);
        this.errorCode = errorCode;
        this.errorAlias = errorAlias;
    }
}
