package com.example.demo.user.service;

import com.example.demo.user.dto.*;
import com.example.demo.user.entity.User;
import com.example.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserJoinResponseDto join(UserJoinDto joinDto){
        if(userRepository.existsByEmail(joinDto.getEmail())){
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }
        if(userRepository.existsByNickname(joinDto.getNickname())){
            throw new IllegalArgumentException("이미 사용 중인 닉네임입니다.");
        }
        String encodedPassword = passwordEncoder.encode(joinDto.getPassword());

        User newUser = User.builder()
                .email(joinDto.getEmail())
                .nickname(joinDto.getNickname())
                .passwordHash(encodedPassword)
                .role("USER")
                .build();

        User savedUser = userRepository.save(newUser);

        return new UserJoinResponseDto(savedUser.getUserId());
    }
    public UserLoginResponseDto login(UserLoginDto userLoginDto) {
        Optional<User> userOptional = userRepository.findByEmail(userLoginDto.getEmail());

        User user = userOptional.orElseThrow(() -> new IllegalArgumentException("이메일 또는 비밀번호가 일치하지 않습니다."));

        if (!passwordEncoder.matches(userLoginDto.getPassword(), user.getPasswordHash())) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 일치하지 않습니다.");
        }
        //TODO;
        String accessToken = "eyJhbGciOiJIUzI1NiIsInR...";
        String refreshToken = "d7fj3kdm20dmfj3jf3...";

        UserResponseDto userResponseDto = new UserResponseDto(
                user.getUserId(),
                user.getEmail(),
                user.getNickname(),
                user.getRole(),
                user.getCreatedAt().toString(),
                user.getUpdatedAt().toString()
        );
        return new UserLoginResponseDto(accessToken, refreshToken, userResponseDto);
    }
        public UserResponseDto getUserInfo(String userId){
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
            return new UserResponseDto(
                    user.getUserId(),
                    user.getEmail(),
                    user.getNickname(),
                    user.getRole(),
                    user.getCreatedAt().toString(),
                    user.getUpdatedAt().toString()
            );
    }
    @Transactional
    public UserResponseDto updateUserInfo(String userId, UserUpdateDto updateDto){
       User user = userRepository.findById(userId)
               .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

       if(updateDto.getNickname() != null && !updateDto.getNickname().isEmpty()){
           if(userRepository.existsByNickname(updateDto.getNickname()) && !user.getNickname().equals(updateDto.getNickname())){
               throw new IllegalArgumentException("이미 사용 중인 닉네임 입니다.");
           }
           user.updateNickname(updateDto.getNickname());
       }
       if(updateDto.getOldPassword() != null && updateDto.getNewPassword() != null){
           if(!passwordEncoder.matches(updateDto.getOldPassword(), user.getPasswordHash())){
               throw new IllegalArgumentException("기존 비밀번호가 일치하지 않습니다.");
           }
           user.updatePasswordHash(passwordEncoder.encode(updateDto.getNewPassword()));
       }
       return new UserResponseDto(
               user.getUserId(),
               user.getEmail(),
               user.getNickname(),
               user.getRole(),
               user.getCreatedAt().toString(),
               user.getUpdatedAt().toString()
       );
    }

    }

