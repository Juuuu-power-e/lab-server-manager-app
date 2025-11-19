package com.labadmin.service;

import com.labadmin.domain.user.User;
import com.labadmin.repository.UserRepository;
import com.labadmin.web.dto.auth.LoginRequest;
import com.labadmin.web.dto.auth.LoginResponse;
import com.labadmin.web.error.BusinessException;
import com.labadmin.web.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider; // 직접 구현한다고 가정

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new BusinessException(ErrorCode.AUTH_INVALID_CREDENTIAL));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BusinessException(ErrorCode.AUTH_INVALID_CREDENTIAL);
        }

        String token = jwtTokenProvider.generateToken(user.getId(), user.getRole().name());

        return new LoginResponse(token, user.getId(), user.getUsername(), user.getRole().name());
    }
}
