package com.modoo.domain.metadata.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.modoo.domain.metadata.entity.RegionDong;
import lombok.Builder;
import lombok.Getter;
import org.locationtech.jts.geom.Point;

@Getter
@Builder
public class MainRegionDto {
    private Long sidoCd;
    private String sidoName;
    private Long sigunguCd;
    private String sigunguName;
    private Long dongCd;
    private String dongName;

    @JsonIgnore
    private Point position;

    public static MainRegionDto from(RegionDong regionDong) {
        return MainRegionDto.builder()
                .sidoCd(regionDong.getSigunguCd().getSidoCd().getSidoCd())
                .sidoName(regionDong.getSigunguCd().getSidoCd().getSidoName())
                .sigunguCd(regionDong.getSigunguCd().getSigunguCd())
                .sigunguName(regionDong.getSigunguCd().getSigunguName())
                .dongCd(regionDong.getDongCd())
                .dongName(regionDong.getDongName())
                .position(regionDong.getPosition())
                .build();
    }
}
