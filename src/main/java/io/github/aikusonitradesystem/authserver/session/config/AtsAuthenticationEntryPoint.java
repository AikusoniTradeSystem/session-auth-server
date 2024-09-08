package io.github.aikusonitradesystem.authserver.session.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.github.aikusonitradesystem.core.constants.ErrorCode;
import io.github.aikusonitradesystem.mvcstandard.model.view.ATSResponseBody;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

import static io.github.aikusonitradesystem.core.utils.MessageUtils.m;

@Slf4j
@RequiredArgsConstructor
public class AtsAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String accept = request.getHeader("Accept");

        response.setStatus(401);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ATSResponseBody<Void> responseBody = ATSResponseBody.<Void>error(
                ErrorCode.UNAUTHORIZED
                , "ATE000001"
                , m("session.need_login_auth")
        );

        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        String jsonBody = ow.writeValueAsString(responseBody);
        response.getWriter().write(jsonBody);
    }
}
