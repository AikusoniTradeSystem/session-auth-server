package io.github.aikusoni.ats.spring.session.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginDto {
    private String loginState;
}
