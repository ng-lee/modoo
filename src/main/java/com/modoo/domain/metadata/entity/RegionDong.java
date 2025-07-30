package com.modoo.domain.metadata.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.modoo.domain.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.locationtech.jts.geom.Point;

@Entity
@Table(name = "region_dong")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RegionDong extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dongCd;

    @Column(length = 50, nullable = false)
    @Comment(value = "읍면동 이름")
    private String dongName;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sigungu_cd")
    @Comment(value = "시군구 코드")
    private RegionSigungu sigunguCd;

    @Column(columnDefinition = "geometry(Point, 4326)", nullable = false)
    @Comment("위도,경도 좌표")
    private Point position;
}
