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
public class PlaceDetailResponseDto {
    private String placeId;
    private String name;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String address;
    private String description;
    private String apiPlaceId;
    private List<String> imageUrls; // 이미지 URL 리스트
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PlaceDetailResponseDto(Place place) {
        this.placeId = place.getPlaceId();
        this.name = place.getName();
        this.latitude = place.getLatitude();
        this.longitude = place.getLongitude();
        this.address = place.getAddress();
        this.description = place.getDescription();
        this.apiPlaceId = place.getApiPlaceId();
        // PlaceImage 엔티티에서 imageUrls 추출
        this.imageUrls = place.getImages().stream()
                .map(PlaceImage::getImageUrl)
                .collect(Collectors.toList());
        this.createdAt = place.getCreatedAt();
        this.updatedAt = place.getUpdatedAt();
    }
}