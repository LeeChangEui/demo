package com.example.demo.place.dto;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PlaceUpdateRequestDto { // placeUpdateDTO
    private String name;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String address;
    private String description;
    private String imageUrl;
    private String apiPlaceId;
}
