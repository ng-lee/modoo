package com.modoo.domain.metadata.controller;

import com.modoo.domain.metadata.dto.MetaDataDto;
import com.modoo.domain.metadata.service.MetaDataService;
import com.modoo.global.constant.MetaDataType;
import com.modoo.global.dto.response.CommonResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final MetaDataService metaDataService;

    @ResponseBody
    @GetMapping("/api/category")
    public CommonResponseDto<List<MetaDataDto>> getCategoryList() {
        List<MetaDataDto> categoryList = metaDataService.findByMetadataType(MetaDataType.CATEGORY.getType());
        return CommonResponseDto.success(categoryList);
    }
}

