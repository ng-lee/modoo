package com.modoo.global.dto;

import com.modoo.global.constant.ResponseMsg;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommonResponseDto<T> {

    private boolean success;
    private String message;
    private T data;

    public static <T> CommonResponseDto<T> success(T data) {
        return new CommonResponseDto<>(true, ResponseMsg.SUCCESS.getMessage(), data);
    }

    public static <T> CommonResponseDto<T> error(ResponseMsg responseMsg) {
        return new CommonResponseDto<>(false, responseMsg.getMessage(), null);
    }
}
