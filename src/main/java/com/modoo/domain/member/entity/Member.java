package com.modoo.domain.member.entity;

import com.modoo.domain.common.entity.BaseEntity;
import com.modoo.domain.member.dto.request.MemberRequest;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

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

    @Column(length = 10, nullable = false)
    @Comment(value = "이름")
    private Integer categoryCd;

    @Column(length = 10, nullable = false)
    @Comment(value = "지역 코드")
    private Integer regionCd;

    @Comment(value = "프로필 사진")
    private Integer profile;

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

    public static Member of(MemberRequest request) {
        return Member.builder()
                .memberId(request.getMemberId())
                .password(request.getPassword())
                .name(request.getName())
                .phone(request.getPhone())
                .categoryCd(request.getCategoryCd())
                .regionCd(request.getRegionCd())
                .profile(request.getProfile())
                .build();
    }

}
