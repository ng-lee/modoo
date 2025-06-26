package com.modoo.domain.metadata.dto;

import com.modoo.domain.metadata.entity.RegionSido;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class RegionSidoDto {
    private Long sidoCd;
    private String sidoName;

    public static RegionSidoDto fromEntity(RegionSido regionSido) {
        return RegionSidoDto.builder()
                .sidoCd(regionSido.getSidoCd())
                .sidoName(regionSido.getSidoName())
                .build();
    }
}
