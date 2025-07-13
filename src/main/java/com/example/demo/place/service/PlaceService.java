package com.example.demo.place.service;

import com.example.demo.place.dto.PlaceCreateRequestDto;
import com.example.demo.place.dto.PlaceCreateResponseDto;
import com.example.demo.place.dto.PlaceDetailResponseDto;
import com.example.demo.place.dto.PlaceSearchResponseDto;
import com.example.demo.place.dto.PlaceUpdateRequestDto;
import com.example.demo.place.dto.PlaceUpdateResponseDto;

import java.util.List;

public interface PlaceService {

    // 1. 장소 생성
    PlaceCreateResponseDto createPlace(PlaceCreateRequestDto requestDto);

    // 2. 모든 장소 조회 (검색 리스트 형태) - 검색 키워드 파라미터 추가
    List<PlaceSearchResponseDto> searchAllPlaces(String keyword);

    // 3. 특정 장소 상세 조회
    PlaceDetailResponseDto getPlaceById(String placeId);

    // 4. 특정 장소 수정
    PlaceUpdateResponseDto updatePlace(String placeId, PlaceUpdateRequestDto requestDto);

    // 5. 특정 장소 삭제
    void deletePlace(String placeId);
}