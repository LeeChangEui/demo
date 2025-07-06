package com.example.demo.place.controller;


import com.example.demo.place.dto.*;
import com.example.demo.place.service.PlaceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/places")
@RequiredArgsConstructor
public class PlaceController {
    private final PlaceService placeService;

    @PostMapping
    public ResponseEntity<PlaceCreateResponseDto> createPlace(@Valid @RequestBody PlaceCreateRequestDto requestDto) {
        PlaceCreateResponseDto responseDto = placeService.createPlace(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<PlaceSearchResponseDto>> getAllPlaces() {
        List<PlaceSearchResponseDto> responseDto = placeService.getAllPlaces();
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{placeId}")
    public ResponseEntity<PlaceDetailResponseDto> getPlaceById(@PathVariable String placeId) {
        try {
            PlaceDetailResponseDto responseDto = placeService.getPlaceById(placeId);
            return ResponseEntity.ok(responseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{placeId}")
    public ResponseEntity<PlaceUpdateResponseDto> updatePlace(
            @PathVariable String placeId,
            @Valid @RequestBody PlaceUpdateRequestDto requestDto) {
        try {
            PlaceUpdateResponseDto responseDto = placeService.updatePlace(placeId, requestDto);
            return ResponseEntity.ok(responseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{placeId}")
    public ResponseEntity<Void> deletePlace(@PathVariable String placeId) {
        try {
            placeService.deletePlace(placeId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
