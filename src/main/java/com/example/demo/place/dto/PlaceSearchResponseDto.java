package com.example.demo.place.dto;

import com.example.demo.place.entity.Place;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlaceSearchResponseDto { //장소배열
    private String placeId;
    private String name;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String address;
    private String description;
    private String imageUrl;

    public PlaceSearchResponseDto(Place place){
        this.placeId = place.getPlaceId();
        this.name = place.getName();
        this.latitude = place.getLatitude();
        this.longitude = place.getLongitude();
        this.address = place.getAddress();
        this.description = place.getDescription();
        this.imageUrl = place.getImageUrl();
    }
}


