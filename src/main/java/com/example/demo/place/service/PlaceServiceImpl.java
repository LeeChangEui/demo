package com.example.demo.place.service;


import com.example.demo.place.dto.*;
import com.example.demo.place.entity.Place;
import com.example.demo.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService{

    private final PlaceRepository placeRepository;


    //장소생성
    @Override
    @Transactional
    public PlaceCreateResponseDto createPlace(PlaceCreateRequestDto requestDto) {
        Place place = new Place(
                requestDto.getName(),
                requestDto.getLatitude(),
                requestDto.getLongitude(),
                requestDto.getAddress(),
                requestDto.getDescription(),
                requestDto.getImageUrl(),
                requestDto.getApiPlaceId()
        );
        Place savedPlace = placeRepository.save(place);
        return new PlaceCreateResponseDto(savedPlace.getPlaceId());
    }

    //모든 장소 조회
    @Override
    @Transactional
    public List<PlaceSearchResponseDto> getAllPlaces() {
        List<Place>places = placeRepository.findAll();
        return places.stream()
                .map(PlaceSearchResponseDto::new)
                .collect(Collectors.toList());
    }


    //특정 장소 상세 조회
    @Override
    @Transactional
    public PlaceDetailResponseDto getPlaceById(String placeId) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(()-> new IllegalArgumentException("Place not found with id"+placeId));
        return new  PlaceDetailResponseDto(place);
    }

    //특정 장소 수정
    @Override
    public PlaceUpdateResponseDto updatePlace(String placeId, PlaceUpdateRequestDto requestDto) {
        Place existingPlace = placeRepository.findById(placeId)
                .orElseThrow(()-> new IllegalArgumentException("Place not found with id"+placeId));

        if(requestDto.getName()!=null){
            existingPlace.setName(requestDto.getName());
        }
        if(requestDto.getLatitude()!=null){
            existingPlace.setLatitude(requestDto.getLatitude());
        }
        if(requestDto.getLongitude()!=null){
            existingPlace.setLongitude(requestDto.getLongitude());
        }
        if(requestDto.getAddress()!=null){
            existingPlace.setAddress(requestDto.getAddress());
        }
        if(requestDto.getDescription()!=null){
            existingPlace.setDescription(requestDto.getDescription());
        }
        if(requestDto.getImageUrl()!=null){
            existingPlace.setImageUrl(requestDto.getImageUrl());
        }
        if(requestDto.getApiPlaceId()!=null){
            existingPlace.setApiPlaceId(requestDto.getApiPlaceId());
        }

        return new PlaceUpdateResponseDto(existingPlace.getPlaceId(), existingPlace.getUpdatedAt());
    }

    @Override
    @Transactional
    public void deletePlace(String placeId) {
        Place placeToDelete = placeRepository.findById(placeId)
                .orElseThrow(()-> new IllegalArgumentException("Place not found with id"+placeId));
        placeRepository.delete(placeToDelete);

    }

}
