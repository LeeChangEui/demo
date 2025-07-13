package com.example.demo.place.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal; // 추가: 위도, 경도, 주소, apiPlaceId는 요청 시에도 필요
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PlaceCreateRequestDto {
    private String name;
    private BigDecimal latitude; // 장소 등록 시 위도
    private BigDecimal longitude; // 장소 등록 시 경도
    private String address;      // 장소 등록 시 주소
    private String description;
    private List<String> imageUrls; // 이미지 URL 리스트
    private String apiPlaceId;   // 외부 API 장소 ID
}