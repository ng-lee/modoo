package com.modoo.domain.metadata.entity;

import com.modoo.domain.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;

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

    @Column(length = 10, nullable = false)
    @Comment(value = "시군구 코드")
    private Integer sigunguCd;

    @Column(length = 10, nullable = false)
    @Comment(value = "위도")
    private BigDecimal latitude;

    @Column(length = 10, nullable = false)
    @Comment(value = "경도")
    private BigDecimal longitude;
}
