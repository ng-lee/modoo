package com.modoo.domain.member.controller;

import com.modoo.domain.member.dto.auth.MemberAuth;
import com.modoo.domain.member.dto.request.MemberRequest;
import com.modoo.domain.member.service.MemberService;
import com.modoo.domain.metadata.dto.MetaDataDto;
import com.modoo.domain.metadata.dto.RegionSidoDto;
import com.modoo.domain.metadata.service.MetaDataService;
import com.modoo.domain.metadata.service.RegionService;
import com.modoo.global.constant.MetaDataType;
import com.modoo.global.constant.ResponseCode;
import com.modoo.global.dto.response.CommonResponseDto;
import com.modoo.global.error.exception.FileUploadException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MetaDataService metaDataService;
    private final RegionService regionService;

    @GetMapping("/myPage")
    public String toMyPage(@AuthenticationPrincipal MemberAuth memberAuth, Model model) {
        model.addAttribute("member", memberAuth);
        return "/member/myPage";
    }

    @GetMapping("/join")
    public String joinPage(Model model) {
        // 카테고리 리스트 조회
        List<MetaDataDto> categoryList = metaDataService.findByMetadataType(MetaDataType.CATEGORY.getType());
        model.addAttribute("categoryList", categoryList);
        // 시도 리스트 조회
        List<RegionSidoDto> sidoList = regionService.findAllSido();
        model.addAttribute("sidoList", sidoList);
        return "/member/join";
    }

    @ResponseBody
    @PostMapping("/join")
    public CommonResponseDto join(@Valid MemberRequest memberRequest, BindingResult bindingResult) {
        try {
            // validation error
            if(bindingResult.hasErrors()) {
                Map<String, Object> errorMap = new HashMap<>();
                for(FieldError fieldError : bindingResult.getFieldErrors()) {
                    errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
                }
                return CommonResponseDto.validationError(errorMap);
            }

            // id 중복 체크
            boolean isIdDuplicated = memberService.checkIdDuplicate(memberRequest.getMemberId());
            if(isIdDuplicated) {
                return CommonResponseDto.error(ResponseCode.ID_DUPLICATED);
            }

            // 회원가입 처리
            Long savedMemberCd = memberService.create(memberRequest);
            if(savedMemberCd == null) {
                throw new RuntimeException("회원가입 처리 중 오류 발생");
            }

            return CommonResponseDto.success(savedMemberCd);
        } catch (FileUploadException e) {
            return CommonResponseDto.error(ResponseCode.FILE_UPLOAD_ERROR, e.getMessage());
        } catch (Exception e) {
            return CommonResponseDto.error(ResponseCode.SERVER_ERROR);
        }
    }

}
