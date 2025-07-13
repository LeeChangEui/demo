package com.example.demo.place.repository;

import com.example.demo.place.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, String> {
    Optional<Place> findByName(String name);
    List<Place> findByNameContaining(String keyword);
}