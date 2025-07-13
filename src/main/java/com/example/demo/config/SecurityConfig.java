package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // <-- 이 임포트 추가
import org.springframework.security.crypto.password.PasswordEncoder; // <-- 이 임포트 추가
import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.csrf.CookieCsrfTokenRepository; // CSRF 활성화 시 사용
// import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler; // CSRF 활성화 시 사용

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // --- 이 부분을 추가해주세요! ---
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    // ----------------------------

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // 개발 단계에서 Postman 등으로 테스트할 때 CSRF 비활성화
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/places/**").permitAll() // "/api/places/**" 경로 모든 요청 허용
                        .requestMatchers("/api/users/**").permitAll()
                        .anyRequest().permitAll() // 모든 엔드포인트에 대한 접근을 허용 (개발용으로 가장 간단)
                )
                .httpBasic(httpBasic -> httpBasic.disable())
                .formLogin(formLogin -> formLogin.disable());

        return http.build();
    }
}