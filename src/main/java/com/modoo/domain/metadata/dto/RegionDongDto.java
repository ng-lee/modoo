package com.modoo.domain.metadata.dto;

import com.modoo.domain.metadata.entity.RegionDong;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@ToString
public class RegionDongDto {
    private Long dongCd;
    private String dongName;
    private Integer sigunguCd;
    private BigDecimal latitude;
    private BigDecimal longitude;

    public static RegionDongDto fromEntity(RegionDong regionDong) {
        return RegionDongDto.builder()
                .dongCd(regionDong.getDongCd())
                .dongName(regionDong.getDongName())
                .sigunguCd(regionDong.getSigunguCd())
                .latitude(regionDong.getLatitude())
                .latitude(regionDong.getLongitude())
                .build();
    }
}
