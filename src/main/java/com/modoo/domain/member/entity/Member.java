package com.modoo.domain.member.entity;

import com.modoo.domain.common.entity.BaseEntity;
import com.modoo.domain.member.dto.request.MemberRequest;
import com.modoo.domain.metadata.entity.RegionDong;
import com.modoo.global.entity.ImageFile;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.JdbcTypeCode;
import org.locationtech.jts.geom.Point;

import java.sql.Types;
import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberCd;

    @Column(length = 20)
    @Comment(value = "회원 아이디")
    private String memberId;

    @Comment(value = "비밀번호")
    private String password;

    @Column(length = 50, nullable = false)
    @Comment(value = "이름")
    private String name;

    @Column(length = 20, nullable = false)
    @Comment(value = "휴대폰 번호 (010-1234-4567)")
    private String phone;

    @OneToOne
    @JoinColumn(name = "file_cd")
    @Comment(value = "프로필 사진")
    private ImageFile profile;

    @Column(length = 10, nullable = false)
    @Comment(value = "카테고리 코드")
    private Integer categoryCd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_cd", nullable = false)
    @Comment(value = "지역 코드")
    private RegionDong region;

    @JdbcTypeCode(Types.OTHER)
    @Column(columnDefinition = "geometry(Point, 4326)", nullable = false)
    @Comment("위도,경도 좌표")
    private Point position;

    @Comment(value = "refresh token")
    private String refreshToken;

    @Comment(value = "refresh token 만료 시간")
    private LocalDateTime refreshTokenExpirationTime;

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void updateRefreshTokenExpirationTime(LocalDateTime refreshTokenExpirationTime) {
        this.refreshTokenExpirationTime = refreshTokenExpirationTime;
    }

    public Member attachProfile(ImageFile imageFile) {
        this.profile = imageFile;
        return this;
    }

    public static Member of(MemberRequest request, RegionDong region) {
        return Member.builder()
                .memberId(request.getMemberId())
                .password(request.getPassword())
                .name(request.getName())
                .phone(request.getPhone())
                .categoryCd(Integer.parseInt(request.getCategoryCd()))
                .region(region)
                .position(region.getPosition())
                .build();
    }

}
