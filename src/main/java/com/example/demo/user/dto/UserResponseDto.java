package com.example.demo.user.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponseDto {
    private String userId;
    private String email;
    private String nickname;
    private String role;
    private String createAt;
    private String updateAt;
}
