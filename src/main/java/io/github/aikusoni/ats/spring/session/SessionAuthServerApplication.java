package io.github.aikusoni.ats.spring.session;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication(scanBasePackages = "io.github.aikusoni")
@ConfigurationPropertiesScan(basePackages = "io.github.aikusoni")
public class SessionAuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SessionAuthServerApplication.class, args);
    }

}
