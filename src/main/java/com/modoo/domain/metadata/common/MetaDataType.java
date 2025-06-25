package com.modoo.domain.metadata.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MetaDataType {
    CATEGORY("category")
    ;

    private String type;
}
