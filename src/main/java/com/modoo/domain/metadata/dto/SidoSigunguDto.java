package com.modoo.domain.metadata.dto;

import lombok.*;

import java.util.List;

@Getter
@Builder
public class SidoSigunguDto {
    private Long sidoCd;
    private String sidoName;
    private List<SigunguDto> sigunguDtoList;

    @Getter
    @AllArgsConstructor
    public static class SigunguDto {
        private Long sigunguCd;
        private String sigunguName;
    }
}
