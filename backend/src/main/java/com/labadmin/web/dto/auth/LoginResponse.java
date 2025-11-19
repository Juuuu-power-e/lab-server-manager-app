package com.labadmin.web.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {

    private String token;  // JWT
    private Long userId;
    private String username;
    private String role;
}
