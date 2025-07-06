package com.example.demo.place.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlaceUpdateResponseDto { //수정된 정보
    private String placeId;
    private LocalDateTime updateAt;
}
