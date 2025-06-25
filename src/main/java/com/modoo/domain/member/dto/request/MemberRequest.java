package com.modoo.domain.member.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberRequest {

    private String memberId;
    private String password;
    private String name;
    private String phone;
    private Integer categoryCd;
    private Integer regionCd;
    private Integer profile;

}
