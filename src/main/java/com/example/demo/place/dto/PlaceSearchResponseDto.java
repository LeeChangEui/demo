package com.example.demo.place.dto;

import com.example.demo.place.entity.Place;
import com.example.demo.place.entity.PlaceImage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class PlaceSearchResponseDto { // 목록 조회 시 사용
    private String placeId;
    private String name;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String address;
    // 검색 목록에서는 description을 포함하지 않거나, 축약된 형태로 제공 가능
    // private String description;
    private String apiPlaceId;
    private String thumbnailUrl; // 대표 이미지 1개만 필요할 경우
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PlaceSearchResponseDto(Place place) {
        this.placeId = place.getPlaceId();
        this.name = place.getName();
        this.latitude = place.getLatitude();
        this.longitude = place.getLongitude();
        this.address = place.getAddress();
        this.apiPlaceId = place.getApiPlaceId();
        // 첫 번째 이미지를 썸네일로 사용
        this.thumbnailUrl = place.getImages().isEmpty() ? null : place.getImages().get(0).getImageUrl();
        this.createdAt = place.getCreatedAt();
        this.updatedAt = place.getUpdatedAt();
    }
}