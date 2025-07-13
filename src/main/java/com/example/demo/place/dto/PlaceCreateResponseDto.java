package com.example.demo.place.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor // placeId만 받는 생성자를 위해 추가
public class PlaceCreateResponseDto {
    private String placeId;
    // 추가적으로 생성된 장소의 중요한 정보를 담을 수 있음 (예: name)
    // private String name;
}