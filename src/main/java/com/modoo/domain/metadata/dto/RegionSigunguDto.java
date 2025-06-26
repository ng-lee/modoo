package com.modoo.domain.metadata.dto;

import com.modoo.domain.metadata.entity.RegionSigungu;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class RegionSigunguDto {
    private Long sigunguCd;
    private String sigunguName;
    private Integer sidoCd;

    public static RegionSigunguDto fromEntity(RegionSigungu regionSigungu) {
        return RegionSigunguDto.builder()
                .sigunguCd(regionSigungu.getSigunguCd())
                .sigunguName(regionSigungu.getSigunguName())
                .sidoCd(regionSigungu.getSidoCd())
                .build();
    }
}
