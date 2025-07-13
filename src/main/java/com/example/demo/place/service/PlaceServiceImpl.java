package com.example.demo.place.service;

import com.example.demo.place.dto.*;
import com.example.demo.place.entity.Place;
import com.example.demo.place.entity.PlaceImage; // PlaceImage 엔티티 import
import com.example.demo.place.repository.PlaceImageRepository; // PlaceImageRepository import
import com.example.demo.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Spring의 @Transactional 사용

import jakarta.persistence.EntityNotFoundException; // jakarta.persistence.EntityNotFoundException 사용

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList; // List 초기화를 위해 추가

@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;
    private final PlaceImageRepository placeImageRepository; // PlaceImageRepository 주입

    // 장소 생성
    @Override
    @Transactional
    public PlaceCreateResponseDto createPlace(PlaceCreateRequestDto requestDto) {
        // 기존 코드에서 Place 생성자에 imageUrl이 있었는데, 이제 images List<PlaceImage>로 관리되므로 변경
        // Place 엔티티의 @Builder를 사용하여 생성하는 것이 좋습니다.
        Place place = Place.builder()
                .name(requestDto.getName())
                .latitude(requestDto.getLatitude())
                .longitude(requestDto.getLongitude())
                .address(requestDto.getAddress())
                .description(requestDto.getDescription())
                // .imageUrl(requestDto.getImageUrl()) // 제거됨
                .apiPlaceId(requestDto.getApiPlaceId())
                .build();

        Place savedPlace = placeRepository.save(place); // 먼저 Place 저장하여 ID 할당

        // 이미지 URL 리스트 저장 (PlaceImage 엔티티 생성 및 관계 설정)
        if (requestDto.getImageUrls() != null && !requestDto.getImageUrls().isEmpty()) {
            List<PlaceImage> placeImages = new ArrayList<>();
            for (int i = 0; i < requestDto.getImageUrls().size(); i++) {
                PlaceImage placeImage = PlaceImage.builder()
                        .imageUrl(requestDto.getImageUrls().get(i))
                        .orderIndex(i) // 순서는 요청된 순서대로 저장
                        .build();
                savedPlace.addImage(placeImage); // Place 엔티티의 편의 메서드 사용 (양방향 관계 설정)
                placeImages.add(placeImage);
            }
            placeImageRepository.saveAll(placeImages); // 일괄 저장
        }

        return new PlaceCreateResponseDto(savedPlace.getPlaceId());
    }

    // 모든 장소 조회 (검색 기능 포함)
    @Override
    @Transactional(readOnly = true) // 읽기 전용 트랜잭션
    public List<PlaceSearchResponseDto> searchAllPlaces(String keyword) { // 파라미터명 변경 및 추가
        List<Place> places;
        if (keyword != null && !keyword.isBlank()) {
            places = placeRepository.findByNameContaining(keyword);
        } else {
            places = placeRepository.findAll();
        }

        return places.stream()
                .map(PlaceSearchResponseDto::new) // DTO 생성자 변경
                .collect(Collectors.toList());
    }

    // 특정 장소 상세 조회
    @Override
    @Transactional(readOnly = true)
    public PlaceDetailResponseDto getPlaceById(String placeId) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new EntityNotFoundException("Place not found with id: " + placeId)); // 예외 타입 변경 (IllegalArgumentException -> EntityNotFoundException)
        return new PlaceDetailResponseDto(place);
    }

    // 특정 장소 수정
    @Override
    @Transactional
    public PlaceUpdateResponseDto updatePlace(String placeId, PlaceUpdateRequestDto requestDto) {
        Place existingPlace = placeRepository.findById(placeId)
                .orElseThrow(() -> new EntityNotFoundException("Place not found with id: " + placeId)); // 예외 타입 변경

        // Place 엔티티의 updatePlaceDetails 메서드 사용
        existingPlace.updatePlaceDetails(
                requestDto.getName(),
                requestDto.getLatitude(),
                requestDto.getLongitude(),
                requestDto.getAddress(),
                requestDto.getDescription(),
                requestDto.getApiPlaceId()
        );

        // 이미지 URL 업데이트 (기존 이미지 삭제 후 새로 추가)
        placeImageRepository.deleteByPlacePlaceId(placeId); // 기존 이미지 모두 삭제
        existingPlace.getImages().clear(); // 엔티티 컬렉션에서도 지워야 함 (orphanRemoval=true 시)

        if (requestDto.getImageUrls() != null && !requestDto.getImageUrls().isEmpty()) {
            List<PlaceImage> newImages = new ArrayList<>();
            for (int i = 0; i < requestDto.getImageUrls().size(); i++) {
                PlaceImage placeImage = PlaceImage.builder()
                        .imageUrl(requestDto.getImageUrls().get(i))
                        .orderIndex(i)
                        .build();
                existingPlace.addImage(placeImage); // Place 엔티티의 편의 메서드 사용
                newImages.add(placeImage);
            }
            placeImageRepository.saveAll(newImages); // 일괄 저장
        }

        // @Transactional 어노테이션 덕분에 existingPlace에 대한 변경은 자동으로 DB에 반영됨
        return new PlaceUpdateResponseDto(existingPlace.getPlaceId(), existingPlace.getUpdatedAt());
    }

    // 특정 장소 삭제
    @Override
    @Transactional
    public void deletePlace(String placeId) {
        Place placeToDelete = placeRepository.findById(placeId)
                .orElseThrow(() -> new EntityNotFoundException("Place not found with id: " + placeId)); // 예외 타입 변경
        placeRepository.delete(placeToDelete);
        // CascadeType.ALL 및 orphanRemoval=true 설정으로 PlaceImage는 자동으로 삭제됨
    }
}