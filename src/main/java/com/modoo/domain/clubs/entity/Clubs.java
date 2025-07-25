package com.modoo.domain.clubs.entity;

import com.modoo.domain.clubs.dto.request.ClubsRequest;
import com.modoo.domain.common.entity.BaseEntity;
import com.modoo.domain.member.dto.request.MemberRequest;
import com.modoo.domain.member.entity.Member;
import com.modoo.domain.metadata.entity.Metadata;
import com.modoo.domain.metadata.entity.RegionDong;
import com.modoo.global.entity.ImageFile;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Entity
@Table(name = "clubs")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Clubs extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clubsCd;

    @Column(length = 20, nullable = false)
    @Comment(value = "모임 이름")
    private String name;

    @Comment(value = "모임 상세 내용")
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_cd")
    @Comment(value = "카테고리 코드")
    private Metadata metadata;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="main_file_cd")
    @Comment(value = "대표이미지 코드")
    private ImageFile imageFile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="region_cd")
    @Comment(value = "지역 코드")
    private RegionDong regionDong;

    @Column(length = 10, nullable = false)
    @Comment(value = "정원")
    private Integer capacity;

    public static Clubs of(ClubsRequest request, RegionDong region, Metadata metadata) {
        return Clubs.builder()
                .name(request.getName())
                .contents(request.getContents())
                .metadata(metadata)
                .capacity(request.getCapacity())
                .regionDong(region)
                .build();
    }
}
