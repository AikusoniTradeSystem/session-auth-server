package io.github.aikusoni.ats.spring.session.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.github.aikusoni.ats.spring.core.constants.ErrorCode;
import io.github.aikusoni.ats.spring.core.exception.BaseAtsException;
import io.github.aikusoni.ats.spring.mvcstandard.model.view.ATSResponseBody;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

@RequiredArgsConstructor
public class AtsAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if (exception instanceof BaseAtsException baseAtsException) {
            response.setStatus(baseAtsException.getErrorCode().getStatusCode());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            ATSResponseBody<Void> responseBody = ATSResponseBody.<Void>error(
                    baseAtsException.getErrorCode()
                    , baseAtsException.getErrorAlias()
                    , baseAtsException.getMessage()
            );

            ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
            String jsonBody = ow.writeValueAsString(responseBody);
            response.getWriter().write(jsonBody);
            return;
        }

        response.setStatus(401);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ATSResponseBody<Void> responseBody = ATSResponseBody.<Void>error(
                ErrorCode.UNAUTHORIZED
                , "AFH-000001"
                , exception.getMessage()
        );

        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        String jsonBody = ow.writeValueAsString(responseBody);
        response.getWriter().write(jsonBody);
    }
}
