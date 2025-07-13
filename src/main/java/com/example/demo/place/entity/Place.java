package com.example.demo.place.entity;

import jakarta.persistence.*; // jakarta.persistence.* 임포트 확인
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList; // ArrayList 임포트 추가
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "place")
@EntityListeners(AuditingEntityListener.class)
public class Place {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "VARCHAR(36)", nullable = false)
    private String placeId;

    @Column(nullable = false)
    private String name;

    @Column(precision = 10, scale = 8)
    private BigDecimal latitude;

    @Column(precision = 11, scale = 8)
    private BigDecimal longitude;

    @Column(length = 500)
    private String address;

    @Lob // TEXT 타입으로 매핑
    private String description;

    @Column(length = 255)
    private String apiPlaceId;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    // PlaceImage와의 1:N 관계 설정
    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, orphanRemoval = true)
    // 이 부분이 핵심! 리스트를 선언과 동시에 초기화해야 합니다.
    private List<PlaceImage> images = new ArrayList<>(); // <-- 이 부분을 추가/수정

    @Builder // @Builder 어노테이션 사용 시, 모든 필드를 포함하는 생성자 대신 Builder 패턴 사용
    public Place(String name, BigDecimal latitude, BigDecimal longitude, String address, String description, String apiPlaceId) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.description = description;
        this.apiPlaceId = apiPlaceId;
        // 빌더를 사용할 경우 images 리스트 초기화는 여기서는 필요하지 않습니다.
        // 위에 필드 선언 시 초기화하는 것이 더 안전하고 간결합니다.
    }

    // 편의 메서드: PlaceImage를 추가할 때 사용
    public void addImage(PlaceImage image) {
        if (this.images == null) { // 이 중복 체크는 이제 필요 없지만, 방어적으로 둘 수 있습니다.
            this.images = new ArrayList<>();
        }
        this.images.add(image);
        image.setPlace(this); // 양방향 관계 설정
    }

    // 장소 세부 정보 업데이트 메서드 (이전에 PlaceServiceImpl에서 사용)
    public void updatePlaceDetails(String name, BigDecimal latitude, BigDecimal longitude, String address, String description, String apiPlaceId) {
        if (name != null) {
            this.name = name;
        }
        if (latitude != null) {
            this.latitude = latitude;
        }
        if (longitude != null) {
            this.longitude = longitude;
        }
        if (address != null) {
            this.address = address;
        }
        if (description != null) {
            this.description = description;
        }
        if (apiPlaceId != null) {
            this.apiPlaceId = apiPlaceId;
        }
        // updatedAt은 @LastModifiedDate가 자동으로 처리합니다.
    }
}