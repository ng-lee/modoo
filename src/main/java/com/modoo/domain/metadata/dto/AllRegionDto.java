package com.modoo.domain.metadata.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.locationtech.jts.geom.Point;

import java.util.List;

@Getter
@Builder
public class AllRegionDto {
    private Long sidoCd;
    private String sidoName;
    private List<SigunguDto> sigunguList;

    @Getter
    @AllArgsConstructor
    public static class SigunguDto {
        private Long sigunguCd;
        private String sigunguName;
        private List<DongDto> dongList;
    }

    @Getter
    @AllArgsConstructor
    public static class DongDto {
        private Long dongCd;
        private String dongName;

        @JsonIgnore
        private Point position;
    }
}
