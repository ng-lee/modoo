package com.modoo.global.config;

import com.modoo.domain.member.service.AuthService;
import com.modoo.global.filter.JwtFilter;
import com.modoo.global.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FilterConfig {

    private final JwtUtil jwtUtil;
    private final AuthService authService;

    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter(jwtUtil, authService);
    }
}
