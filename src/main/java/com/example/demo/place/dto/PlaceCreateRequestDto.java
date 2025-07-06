package com.example.demo.place.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
@Getter
@Setter
public class PlaceCreateRequestDto { //placeCreateDTO

    @NotBlank(message = "장소 이름은 필수 입니다.")
    private String name;

    @NotNull(message = "위도는 필수입니다.")
    private BigDecimal latitude;

    @NotNull(message = "경도는 필수입니다.")
    private BigDecimal longitude;

    private String address;

    private String description;

    private String imageUrl;

    private String apiPlaceId;
}
