package com.modoo.domain.clubs.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClubsRequest {

    private String name;
    private String contents;
    private Integer capacity;
    private Integer categoryCd;
    private Integer regionCd;

}
