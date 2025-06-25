package com.modoo.global.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FileType {
    CLUBS("clubs"),
    PROFILE("profile")
    ;

    private String type;
}
