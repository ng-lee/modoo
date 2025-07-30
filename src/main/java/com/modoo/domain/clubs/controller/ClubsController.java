package com.modoo.domain.clubs.controller;

import com.modoo.domain.clubs.dto.request.ClubsRequest;
import com.modoo.domain.clubs.dto.response.ClubsResponse;
import com.modoo.domain.clubs.entity.Clubs;
import com.modoo.domain.clubs.service.ClubsService;
import com.modoo.domain.metadata.dto.AllRegionDto;
import com.modoo.domain.metadata.dto.RegionSidoDto;
import com.modoo.domain.metadata.service.RegionService;
import com.modoo.global.constant.MetaDataType;
import com.modoo.domain.metadata.dto.MetaDataDto;
import com.modoo.domain.metadata.service.MetaDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @PostMapping(value = "/api/clubs/create",  headers = ("content-type=multipart/*"))
    public Map<String, Object> clubsCreate(@ModelAttribute ClubsRequest clubsRequest, @RequestParam(value = "files", required = false) MultipartFile[] files, @RequestParam(value = "mainFile", required = false) MultipartFile mainFile) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap = clubsService.create(clubsRequest, files, mainFile);
        return resultMap;
    }

    /**
     * 모임 생성 페이지 이동
     */
    @GetMapping("/clubs/detail")
    public String detail(ModelMap modelMap, @RequestParam Integer clubsCd) {
        // 카테고리 리스트 조회
        List<MetaDataDto> categoryList = metaDataService.findByMetadataType(MetaDataType.CATEGORY.getType());
        modelMap.addAttribute("categoryList", categoryList);
        // 시도 리스트 조회
        List<RegionSidoDto> sidoList = regionService.findAllSido();
        modelMap.addAttribute("sidoList", sidoList);

        // 모임 정보 조회
        ClubsResponse clubsResponse = clubsService.getClubsDetail(clubsCd);
        //전체 시군구 리스트
        List<AllRegionDto> allSidoSigunguList = regionService.findAllSidoSigungu();

        modelMap.addAttribute("clubs", clubsResponse);
        modelMap.addAttribute("allRegionList", allSidoSigunguList);
        return "clubs/detail";
    }
}
