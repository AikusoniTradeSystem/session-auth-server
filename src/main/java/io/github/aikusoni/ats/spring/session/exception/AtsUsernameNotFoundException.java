package io.github.aikusoni.ats.spring.session.exception;

import io.github.aikusoni.ats.spring.core.common.BaseErrorCode;
import io.github.aikusoni.ats.spring.core.common.MessageCode;
import io.github.aikusoni.ats.spring.core.exception.BaseAtsException;
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

    public AtsUsernameNotFoundException(BaseErrorCode errorCode, String errorAlias, MessageCode messageCode) {
        super(messageCode.getMessage());
        this.errorCode = errorCode;
        this.errorAlias = errorAlias;
    }
}
