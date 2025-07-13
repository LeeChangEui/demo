package com.example.demo.place.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PlaceUpdateRequestDto {
    private String name;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String address;
    private String description;
    private List<String> imageUrls; // 업데이트될 전체 이미지 URL 목록
    private String apiPlaceId;
}