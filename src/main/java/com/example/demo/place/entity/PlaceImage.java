package com.example.demo.place.entity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "place_image")
public class PlaceImage {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "image_id", columnDefinition = "VARCHAR(36)")
    private String imageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id", nullable = false)
    private Place place;

    @Column(name = "image_url", nullable = false, length = 512)
    private String imageUrl;

    @Column(name = "order_index")
    private Integer orderIndex;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Builder
    public PlaceImage(String imageId, Place place, String imageUrl, Integer orderIndex, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.imageId = imageId;
        this.place = place;
        this.imageUrl = imageUrl;
        this.orderIndex = orderIndex;
        this.createdAt = (createdAt != null) ? createdAt : LocalDateTime.now();
        this.updatedAt = (updatedAt != null) ? updatedAt : LocalDateTime.now();
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public void updateImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        this.updatedAt = LocalDateTime.now();
    }

    public void updateOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
        this.updatedAt = LocalDateTime.now();
    }
}