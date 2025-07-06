package com.example.demo.place.dto;

import com.example.demo.place.entity.Place;
import lombok.*;

import java.time.LocalDateTime;
import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlaceDetailResponseDto { //장소 상세 정보
    private String placeId;
    private String name;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String address;
    private String description;
    private String imageUrl;
    private String apiPlaceId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PlaceDetailResponseDto(Place place){
        this.placeId = place.getPlaceId();
        this.name = place.getName();
        this.latitude = place.getLatitude();
        this.longitude = place.getLongitude();
        this.address = place.getAddress();
        this.description = place.getDescription();
        this.imageUrl = place.getImageUrl();
        this.apiPlaceId = place.getApiPlaceId();
        this.createdAt = place.getCreatedAt();
        this.updatedAt = place.getUpdatedAt();
    }

}
