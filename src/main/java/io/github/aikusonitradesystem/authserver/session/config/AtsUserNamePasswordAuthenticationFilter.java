package io.github.aikusonitradesystem.authserver.session.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
public class AtsUserNamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public AtsUserNamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        UsernamePasswordAuthenticationToken authToken = null;

        String username = null;
        String password = null;

        if (MediaType.APPLICATION_JSON_VALUE.equals(request.getContentType())) {
            try {

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            username = obtainUsername(request);
            password = obtainPassword(request);
        }

        if (username == null || password == null || username.trim().isEmpty() || password.trim().isEmpty()) {
            throw new AuthenticationServiceException("Authentication failed: missing required parameters");
        }

        authToken = new UsernamePasswordAuthenticationToken(username, password);
        this.setDetails(request, authToken);
        return this.getAuthenticationManager().authenticate(authToken);
    }
}
