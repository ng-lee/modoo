package com.modoo.domain.clubs.dto.response;

import com.modoo.domain.clubs.entity.Clubs;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
public class ClubsResponse {

    private Long clubsCd;
    private String name;
    private String contents;
    private Integer capacity;
    private Long categoryCd;
    private Long regionCd;
    private Long mainFileCd;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static ClubsResponse fromEntity(Clubs clubs) {
        return ClubsResponse.builder()
                .clubsCd(clubs.getClubsCd())
                .name(clubs.getName())
                .contents(clubs.getContents())
                .capacity(clubs.getCapacity())
                .categoryCd(clubs.getCategory().getMetadataCd())
                .regionCd(clubs.getRegionDong().getDongCd())
                .mainFileCd(clubs.getImageFile().getFileCd())
                .createdAt(clubs.getCreatedAt())
                .modifiedAt(clubs.getModifiedAt())
                .build();
    }
}
