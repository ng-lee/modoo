package com.modoo.domain.main.controller;

import com.modoo.domain.member.dto.auth.MemberAuth;
import com.modoo.domain.member.service.MemberService;
import com.modoo.domain.metadata.dto.MainRegionDto;
import com.modoo.domain.metadata.dto.AllRegionDto;
import com.modoo.domain.metadata.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final RegionService regionService;
    private final MemberService memberService;

    @GetMapping("/")
    public String home(@AuthenticationPrincipal MemberAuth memberAuth,
                       Model model) {

        // 로그인 x 시 기본값
        Long dongCd = memberAuth == null ? 4084L : memberService.findByMemberCd(memberAuth.getMemberCd()).getRegionCd();

        // 기본 지역
        MainRegionDto mainRegionDto = regionService.getInitialRegionData(dongCd);

        //전체 시군구 리스트
        List<AllRegionDto> allRegionDtoList = regionService.findAllSidoSigungu();

        model.addAttribute("allRegionDtoList", allRegionDtoList);
        model.addAttribute("defaultRegion", mainRegionDto);
        return "/main";
    }

}
