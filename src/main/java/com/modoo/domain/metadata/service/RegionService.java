package com.modoo.domain.metadata.service;

import com.modoo.domain.metadata.dto.MetaDataDto;
import com.modoo.domain.metadata.dto.RegionDongDto;
import com.modoo.domain.metadata.dto.RegionSidoDto;
import com.modoo.domain.metadata.dto.RegionSigunguDto;
import com.modoo.domain.metadata.entity.RegionSido;
import com.modoo.domain.metadata.entity.RegionSigungu;
import com.modoo.domain.metadata.repository.MetadataRepository;
import com.modoo.domain.metadata.repository.RegionDongRepository;
import com.modoo.domain.metadata.repository.RegionSidoRepository;
import com.modoo.domain.metadata.repository.RegionSigunguRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegionService {

    private final RegionSidoRepository regionSidoRepository;
    private final RegionSigunguRepository regionSigunguRepository;
    private final RegionDongRepository regionDongRepository;

    /**
     * 시도 리스트 조회
     * */
    public List<RegionSidoDto> findAllSido() {
        List<RegionSidoDto> sidoList = regionSidoRepository.findAll().stream()
                .map(RegionSidoDto::fromEntity)
                .collect(Collectors.toList());
        return sidoList;
    }

    /**
     * 시군구 리스트 조회
     * */
    public List<RegionSigunguDto> findBySidoCd(Integer sidoCd) {
        List<RegionSigunguDto> sigunguList = regionSigunguRepository.findBySidoCd(sidoCd).stream()
                .map(RegionSigunguDto::fromEntity)
                .collect(Collectors.toList());
        return sigunguList;
    }

    /**
     * 읍면동 리스트 조회
     * */
    public List<RegionDongDto> findBySigunguCd(Integer sigunguCd) {
        List<RegionDongDto> dongList = regionDongRepository.findBySigunguCd(sigunguCd).stream()
                .map(RegionDongDto::fromEntity)
                .collect(Collectors.toList());
        return dongList;
    }
}
