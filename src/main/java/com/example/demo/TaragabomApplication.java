package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing; // 이 임포트 추가

@SpringBootApplication
@EnableJpaAuditing
public class TaragabomApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaragabomApplication.class, args);
	}
}