package com.modoo.domain.member.dto.response;

import com.modoo.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
public class MemberResponse {

    private Long cd;
    private String id;
    private String password;
    private String name;
    private String phone;
    private Integer categoryCd;
    private Integer regionCd;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static MemberResponse fromEntity(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .build();
    }

}
