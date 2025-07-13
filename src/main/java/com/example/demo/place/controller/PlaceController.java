package com.example.demo.place.controller;

import com.example.demo.place.dto.*; // DTO 임포트 변경
import com.example.demo.place.service.PlaceService; // 인터페이스 임포트
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/places")
@RequiredArgsConstructor // final 필드에 대한 생성자 자동 주입
public class PlaceController {

    private final PlaceService placeService; // 인터페이스 주입

    // 장소 생성
    @PostMapping
    public ResponseEntity<PlaceCreateResponseDto> createPlace(@RequestBody PlaceCreateRequestDto requestDto) {
        PlaceCreateResponseDto response = placeService.createPlace(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 모든 장소 조회 (검색 기능 포함)
    @GetMapping
    public ResponseEntity<List<PlaceSearchResponseDto>> searchAllPlaces(
            @RequestParam(value = "keyword", required = false) String keyword) {
        List<PlaceSearchResponseDto> response = placeService.searchAllPlaces(keyword);
        return ResponseEntity.ok(response);
    }

    // 특정 장소 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<PlaceDetailResponseDto> getPlaceById(@PathVariable("id") String placeId) {
        PlaceDetailResponseDto response = placeService.getPlaceById(placeId);
        return ResponseEntity.ok(response);
    }

    // 특정 장소 수정
    @PutMapping("/{id}")
    public ResponseEntity<PlaceUpdateResponseDto> updatePlace(@PathVariable("id") String placeId,
                                                              @RequestBody PlaceUpdateRequestDto requestDto) {
        PlaceUpdateResponseDto response = placeService.updatePlace(placeId, requestDto);
        return ResponseEntity.ok(response);
    }

    // 특정 장소 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlace(@PathVariable("id") String placeId) {
        placeService.deletePlace(placeId);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}