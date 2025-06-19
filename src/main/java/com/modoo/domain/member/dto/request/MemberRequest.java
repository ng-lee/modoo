package com.modoo.domain.member.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberRequest {

    private String id;
    private String password;
    private String name;
    private String phone;
    private Integer categoryCd;
    private Integer regionCd;

}
