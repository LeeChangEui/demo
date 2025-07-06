package com.example.demo.user.repository; // 패키지 경로를 프로젝트에 맞게 조정하세요.


import com.example.demo.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// JpaRepository<엔티티 타입, ID 타입>
public interface UserRepository extends JpaRepository<User, String> {
    // 이메일로 사용자 조회 (로그인 시 필요)
    Optional<User> findByEmail(String email);

    // 이메일 존재 여부 확인 (회원가입 시 중복 체크)
    boolean existsByEmail(String email);

    // 닉네임 존재 여부 확인 (회원가입 시 중복 체크)
    boolean existsByNickname(String nickname);
}