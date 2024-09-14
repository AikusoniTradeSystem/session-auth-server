package io.github.aikusoni.ats.spring.session.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.github.aikusoni.ats.spring.core.constants.ErrorCode;
import io.github.aikusoni.ats.spring.mvcstandard.model.view.ATSResponseBody;
import io.github.aikusoni.ats.spring.session.constants.SessionMessageCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

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
                , SessionMessageCode.NEED_LOGIN_AUTH.getMessage()
        );

        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        String jsonBody = ow.writeValueAsString(responseBody);
        response.getWriter().write(jsonBody);
    }
}
