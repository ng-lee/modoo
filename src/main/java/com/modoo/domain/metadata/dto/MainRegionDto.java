package com.modoo.domain.metadata.dto;

import com.modoo.domain.metadata.entity.RegionDong;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MainRegionDto {
    private Long sidoCd;
    private String sidoName;
    private Long sigunguCd;
    private String sigunguName;
    private Long dongCd;
    private String dongName;

    public static MainRegionDto from(RegionDong regionDong) {
        return MainRegionDto.builder()
                .sidoCd(regionDong.getDongCd())
                .sidoName(regionDong.getDongName())
                .sigunguCd(regionDong.getSigunguCd().getSigunguCd())
                .sigunguName(regionDong.getSigunguCd().getSigunguName())
                .dongCd(regionDong.getDongCd())
                .dongName(regionDong.getDongName())
                .build();
    }
}
