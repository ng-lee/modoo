package com.modoo.domain.member.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class MemberRequest {

    @NotBlank(message = "아이디를 입력해주세요.")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,12}$", message = "아이디는 영문, 숫자 6~12자리여야 합니다.")
    private String memberId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^*()\\-_=+\\\\|\\[\\]{};:'\",.<>/?]).{8,16}$",
            message = "영문 대문자와 소문자, 숫자, 특수문자를 하나 이상 포함하여 8~16자리여야 합니다."
    )
    private String password;

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "휴대폰 번호를 입력해주세요.")
    @Pattern(regexp = "^010?\\d{3,4}?\\d{4}$", message = "유효한 휴대폰 번호를 입력해주세요.")
    private String phone;

    @NotNull(message = "카테고리를 선택해주세요.")
    private String categoryCd;

    @NotNull(message = "지역을 선택해주세요.")
    private String regionCd;

    private MultipartFile profile;

}
