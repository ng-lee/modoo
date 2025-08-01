package com.modoo.domain.metadata.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.modoo.domain.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "region_sigungu")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RegionSigungu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sigunguCd;

    @Column(length = 50, nullable = false)
    @Comment(value = "시군구 이름")
    private String sigunguName;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sido_cd")
    @Comment(value = "시도 코드")
    private RegionSido sidoCd;

    @OneToMany(mappedBy = "sigunguCd", fetch = FetchType.LAZY)
    private List<RegionDong> dongList = new ArrayList<>();
}
