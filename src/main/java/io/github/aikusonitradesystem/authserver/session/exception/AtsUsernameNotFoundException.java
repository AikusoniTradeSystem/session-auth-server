package io.github.aikusonitradesystem.authserver.session.exception;

import io.github.aikusonitradesystem.core.common.BaseErrorCode;
import io.github.aikusonitradesystem.core.exception.BaseAtsException;
import lombok.Getter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Getter
public class AtsUsernameNotFoundException extends UsernameNotFoundException implements BaseAtsException {
    private final BaseErrorCode errorCode;
    private final String errorAlias;

    public AtsUsernameNotFoundException(BaseErrorCode errorCode, String errorAlias, String message) {
        super(message);
        this.errorCode = errorCode;
        this.errorAlias = errorAlias;
    }
}
