package com.modoo.global.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMsg {

    SUCCESS("성공적으로 처리되었습니다."),
    SERVER_ERROR("서버 오류가 발생했습니다.");

    private String message;
}
