package io.github.aikusonitradesystem.authserver.session;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication(scanBasePackages = "io.github.aikusonitradesystem")
@ConfigurationPropertiesScan(basePackages = "io.github.aikusonitradesystem")
public class SessionAuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SessionAuthServerApplication.class, args);
    }

}
