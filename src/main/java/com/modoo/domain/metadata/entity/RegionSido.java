package com.modoo.domain.metadata.entity;

import com.modoo.domain.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "region_sido")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RegionSido extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sidoCd;

    @Column(length = 50, nullable = false)
    @Comment(value = "시도 이름")
    private String sidoName;

    @OneToMany(mappedBy = "sidoCd", fetch = FetchType.LAZY)
    private List<RegionSigungu> sigunguList = new ArrayList<>();
}
