package com.modoo.domain.main.controller;

import com.modoo.domain.metadata.dto.SidoSigunguDto;
import com.modoo.domain.metadata.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final RegionService regionService;

    @GetMapping("/")
    public String home() {

        //전체 시군구 리스트
        List<SidoSigunguDto> allSidoSigunguList = regionService.findAllSidoSigungu();
        return "/main";
    }

}
