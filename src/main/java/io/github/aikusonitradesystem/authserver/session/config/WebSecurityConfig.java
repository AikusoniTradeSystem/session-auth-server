package io.github.aikusonitradesystem.authserver.session.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.aikusonitradesystem.authserver.session.constants.SessionAuthServerErrorCode;
import io.github.aikusonitradesystem.authserver.session.dao.UserDao;
import io.github.aikusonitradesystem.authserver.session.dao.UserRoleDao;
import io.github.aikusonitradesystem.authserver.session.exception.AtsUsernameNotFoundException;
import io.github.aikusonitradesystem.authserver.session.helper.PasswordHelper;
import io.github.aikusonitradesystem.authserver.session.model.dto.UserDto;
import io.github.aikusonitradesystem.authserver.session.properties.AuthServerProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final AuthServerProperties authServerProperties;
    private final ObjectMapper objectMapper;
    private final UserDao userDao;
    private final UserRoleDao userRoleDao;
    private final PasswordHelper passwordHelper;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> {
            web.ignoring().requestMatchers(
                    "/favicon.ico",
                    "/error"
            );
            if (authServerProperties.getAllowSwaggerWithoutLogin()) {
                web.ignoring().requestMatchers(
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html"
                );
            }
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
//                .cors(AbstractHttpConfigurer::disable)
                .csrf(csrf ->
                        csrf.ignoringRequestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html")
                                .ignoringRequestMatchers("/v1/auth/login")
                                .ignoringRequestMatchers("/v1/user/register")
                )
                .formLogin(AbstractHttpConfigurer::disable)
                .anonymous(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                                .requestMatchers("/v1/public/**").permitAll()
                                .requestMatchers("/v1/test/**").permitAll()
                                .requestMatchers("/v1/auth/**").permitAll()
                                .requestMatchers("/v1/user/register").permitAll()
                                .anyRequest().authenticated()
                )
                .securityContext(securityContext ->
                        securityContext
                                .securityContextRepository(new HttpSessionSecurityContextRepository()))
                .sessionManagement(session ->
                        session
                                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                                .maximumSessions(1)
                                .maxSessionsPreventsLogin(true)
                                .expiredUrl("/v1/auth/session-expired")
                )
                .exceptionHandling(customizer ->
                        customizer
                                .authenticationEntryPoint(new AtsAuthenticationEntryPoint(objectMapper))
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/v1/auth/logout")
                                .logoutSuccessUrl("/v1/auth/logout-success")
                                .deleteCookies("JSESSIONID")
                );
        http.addFilterAt(userNamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public UsernamePasswordAuthenticationFilter userNamePasswordAuthenticationFilter() {
        UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter = new AtsUserNamePasswordAuthenticationFilter(
                authenticationManager(
                        userDetailsService(),
                        passwordEncoder()
                ),
                objectMapper
        );
        usernamePasswordAuthenticationFilter.setFilterProcessesUrl("/v1/auth/login");
        usernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler("/v1/auth/login-success"));
        usernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(new AtsAuthenticationFailureHandler(objectMapper));
        usernamePasswordAuthenticationFilter.setSecurityContextRepository(new HttpSessionSecurityContextRepository());
        return usernamePasswordAuthenticationFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService
            , PasswordEncoder passwordEncoder
    ) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setHideUserNotFoundExceptions(false);

        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            UserDto userDto = userDao.getUser(username);
            if (userDto == null) {
                throw new AtsUsernameNotFoundException(SessionAuthServerErrorCode.FAILED_TO_FIND_USER, "UDS-000001", "사용자를 찾을 수 없습니다.");
            }
            if (!authServerProperties.getPasswordEncoderType().equals(passwordHelper.passwordType(userDto.getPassword()))) {
                throw new AtsUsernameNotFoundException(SessionAuthServerErrorCode.PASSWORD_IS_TOO_OLD, "UDS-000002", "비밀번호 인코더가 일치하지 않습니다.");
            }
            List<String> roles = userRoleDao.getRoles(username);
            return new User(userDto.getUsername(), passwordHelper.encodedPassword(userDto.getPassword()), roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return switch (authServerProperties.getPasswordEncoderType()) {
            case "bcrypt" -> new BCryptPasswordEncoder();
            default -> throw new IllegalArgumentException("지원하지 않는 비밀번호 인코더입니다.");
        };
    }

//    @Bean
//    public StrictHttpFirewall httpFirewall() {
//        StrictHttpFirewall httpFirewall = new StrictHttpFirewall();
//        return httpFirewall;
//    }
}
