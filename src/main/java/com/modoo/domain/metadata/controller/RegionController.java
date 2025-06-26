package com.modoo.domain.metadata.controller;

import com.modoo.domain.metadata.dto.RegionDongDto;
import com.modoo.domain.metadata.dto.RegionSidoDto;
import com.modoo.domain.metadata.dto.RegionSigunguDto;
import com.modoo.domain.metadata.service.RegionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RegionController {

    private final RegionService regionService;

    /**
     * 시도 리스트 조회
     * */
    @ResponseBody
    @PostMapping("/api/region/getSidoList")
    public List<RegionSidoDto> getSidoList() {
        List<RegionSidoDto> sidoList = regionService.findAllSido();
        return sidoList;
    }

    /**
     * 시군구 리스트 조회
     * */
    @ResponseBody
    @PostMapping("/api/region/getSigunguList")
    public List<RegionSigunguDto> getSigunguList(@RequestParam Integer sidoCd) {
        List<RegionSigunguDto> sigunguList = regionService.findBySidoCd(sidoCd);
        return sigunguList;
    }

    /**
     * 읍면동 리스트 조회
     * */
    @ResponseBody
    @PostMapping("/api/region/getDongList")
    public List<RegionDongDto> getDongList(@RequestParam Integer sigunguCd) {
        List<RegionDongDto> dongList = regionService.findBySigunguCd(sigunguCd);
        return dongList;
    }
}

