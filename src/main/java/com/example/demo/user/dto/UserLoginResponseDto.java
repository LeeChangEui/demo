package com.example.demo.user.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserLoginResponseDto {
    private String accessToken;
    private String refreshToken;
    private UserResponseDto user;
}
