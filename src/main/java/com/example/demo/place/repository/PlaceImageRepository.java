package com.example.demo.place.repository;

import com.example.demo.place.entity.PlaceImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceImageRepository extends JpaRepository<PlaceImage, String> {
    List<PlaceImage> findByPlacePlaceIdOrderByOrderIndexAsc(String placeId);
    void deleteByPlacePlaceId(String placeId);
}