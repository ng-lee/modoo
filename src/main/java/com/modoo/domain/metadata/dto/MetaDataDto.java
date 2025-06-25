package com.modoo.domain.metadata.dto;

import com.modoo.domain.metadata.entity.Metadata;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class MetaDataDto {
    private Long metadataCd;
    private String metadataType;
    private String contents;
    private Integer metadataOrder;

    public static MetaDataDto fromEntity(Metadata metaData) {
        return MetaDataDto.builder()
                .metadataCd(metaData.getMetadataCd())
                .metadataType(metaData.getMetadataType())
                .contents(metaData.getContents())
                .metadataOrder(metaData.getMetadataOrder())
                .build();
    }
}
