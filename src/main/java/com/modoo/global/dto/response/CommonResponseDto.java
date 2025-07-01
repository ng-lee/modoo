package com.modoo.global.dto.response;

import com.modoo.global.constant.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class CommonResponseDto<T> {

    private boolean success; // 성공 여부
    private String code; // 응답 코드 (SUCCESS, VALIDATION_ERROR..)
    private String message; // 응답 메시지
    private T data; // 결과 데이터

    /**
     * 성공 응답 객체
     * @param data
     * @param responseCode
     * @return
     * @param <T>
     */
    public static <T> CommonResponseDto<T> success(T data, ResponseCode responseCode) {
        return new CommonResponseDto<>(true, responseCode.getCode(), responseCode.getMessage(), data);
    }

    /**
     * 성공 응답 객체 (응답 코드 X)
     * @param data
     * @return
     * @param <T>
     */
    public static <T> CommonResponseDto<T> success(T data) {
        return new CommonResponseDto<>(true, null, null, data);
    }

    /**
     * 에러 발생 시 응답 객체
     * @param responseCode
     * @return
     * @param <T>
     */
    public static <T> CommonResponseDto<T> error(ResponseCode responseCode) {
        return new CommonResponseDto<>(false, responseCode.getCode(), responseCode.getMessage(), null);
    }

    /**
     * 에러 발생 시 메시지 커스텀하여 응답 객체 생성
     * @param responseCode
     * @param customMessage
     * @return
     * @param <T>
     */
    public static <T> CommonResponseDto<T> error(ResponseCode responseCode, String customMessage) {
        return new CommonResponseDto<>(false, responseCode.getCode(), customMessage, null);
    }

    /**
     * validation 에러 발생 시 응답 객체
     * @param errorMap
     * @return
     */
    public static CommonResponseDto<Map<String, Object>> validationError(Map<String, Object> errorMap) {
        return new CommonResponseDto<>(false, ResponseCode.VALIDATION_ERROR.getCode(), ResponseCode.VALIDATION_ERROR.getMessage(), errorMap);
    }
}
