package com.example.demo.user.contorller;

import com.example.demo.user.dto.*;
import com.example.demo.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<UserJoinResponseDto> join(@RequestBody UserJoinDto joinDto) {
        UserJoinResponseDto response = userService.join(joinDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody UserLoginDto loginDto) {
        UserLoginResponseDto response = userService.login(loginDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getMyInfo(@RequestHeader(value = "Authorization", required = false) String accessToken) {
        // 방금 MySQL Workbench에서 확인한 실제 user_id (UUID) 값을 여기에 넣습니다.
        String sampleUserId = "335cc8b4-58dd-11f0-94e7-cafe45125383"; // 실제 UUID로 교체
        UserResponseDto userInfo = userService.getUserInfo(sampleUserId);
        return ResponseEntity.ok(userInfo);
    }

    @PutMapping("/me")
    public ResponseEntity<UserResponseDto> updateMyInfo(
            @RequestBody UserUpdateDto updateDto) {
        // 방금 MySQL Workbench에서 확인한 실제 user_id (UUID) 값을 여기에 넣습니다.
        String userIdToUpdate = "335cc8b4-58dd-11f0-94e7-cafe45125383"; // 실제 UUID로 교체
        UserResponseDto updatedUser = userService.updateUserInfo(userIdToUpdate, updateDto);
        return ResponseEntity.ok(updatedUser);
    }
}
