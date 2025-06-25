package com.modoo.domain.main.controller;

import com.modoo.domain.metadata.common.MetaDataType;
import com.modoo.domain.metadata.dto.MetaDataDto;
import com.modoo.domain.metadata.service.MetaDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MetaDataService metaDataService;

    @GetMapping("/")
    public String home() {
        return "/main";
    }

    @ResponseBody
    @GetMapping("/api/category")
    public List<MetaDataDto> getCategoryList() {
        List<MetaDataDto> categoryList = metaDataService.findByMetadataType(MetaDataType.CATEGORY.getType());
        return categoryList;
    }
}
