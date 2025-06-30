package com.modoo.global.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {

    SUCCESS("SUCCESS", "성공적으로 처리되었습니다."),
    ID_DUPLICATED("ID_DUPLICATED", "이미 존재하는 아이디입니다."),
    VALIDATION_ERROR("VALIDATION_ERROR", "유효성 검증에 실패하였습니다."),
    FILE_UPLOAD_ERROR("FILE_UPLOAD_ERROR", "파일 업로드 도중 오류 발생했습니다."),
    SERVER_ERROR("SERVER_ERROR", "서버 오류가 발생했습니다.")
    ;

    private String code; // 응답 코드
    private String message; // 응답 메시지
}
