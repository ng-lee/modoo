package com.modoo.domain.clubs.controller;

import com.modoo.domain.clubs.dto.request.ClubsRequest;
import com.modoo.domain.clubs.service.ClubsService;
import com.modoo.domain.metadata.dto.RegionSidoDto;
import com.modoo.domain.metadata.service.RegionService;
import com.modoo.global.constant.MetaDataType;
import com.modoo.domain.metadata.dto.MetaDataDto;
import com.modoo.domain.metadata.service.MetaDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ClubsController {

    private final MetaDataService metaDataService;
    private final ClubsService clubsService;
    private final RegionService regionService;

    /**
     * 모임 생성 페이지 이동
     */
    @GetMapping("/clubs/create")
    public String create(ModelMap modelMap) {
        // 카테고리 리스트 조회
        List<MetaDataDto> categoryList = metaDataService.findByMetadataType(MetaDataType.CATEGORY.getType());
        modelMap.addAttribute("categoryList", categoryList);
        // 시도 리스트 조회
        List<RegionSidoDto> sidoList = regionService.findAllSido();
        modelMap.addAttribute("sidoList", sidoList);
        return "/clubs/create";
    }

    /**
     * 모임 생성
     */
    @ResponseBody
    @PostMapping("/api/clubs/create")
    public Map<String, Object> clubsCreate(@RequestBody ClubsRequest clubsRequest) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap = clubsService.create(clubsRequest);
        return resultMap;
    }

    
}
