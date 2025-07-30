package com.modoo.domain.member.dto;

import com.modoo.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
public class MemberDto {

    private Long memberCd;
    private String memberId;
    private String password;
    private String name;
    private String phone;
    private Integer categoryCd;
    private Long regionCd;
    private Integer profile;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static MemberDto fromEntity(Member member) {
        return MemberDto.builder()
                .memberCd(member.getMemberCd())
                .memberId(member.getMemberId())
                .name(member.getName())
                .regionCd(member.getRegion().getDongCd())
                .build();
    }

}
