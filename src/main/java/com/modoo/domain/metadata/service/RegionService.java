package com.modoo.domain.metadata.service;

import com.modoo.domain.metadata.dto.*;
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
        List<RegionSigunguDto> sigunguList = regionSigunguRepository.findBySidoCd_sidoCd(Long.valueOf(sidoCd)).stream()
                .map(RegionSigunguDto::fromEntity)
                .collect(Collectors.toList());
        return sigunguList;
    }

    /**
     * 읍면동 리스트 조회
     * */
    public List<RegionDongDto> findBySigunguCd(Integer sigunguCd) {
        List<RegionDongDto> dongList = regionDongRepository.findBySigunguCd_sigunguCd(Long.valueOf(sigunguCd)).stream()
                .map(RegionDongDto::fromEntity)
                .collect(Collectors.toList());
        return dongList;
    }

    /**
     * 지역 드롭다운 세팅
     */
    public MainRegionDto getInitialRegionData(Long dongCd) {
        return MainRegionDto.from(regionDongRepository.getInitialRegionData(dongCd));
    }


    /**
     * 전체 시-구-동 리스트 조회
     */
    public List<AllRegionDto> findAllSidoSigungu() {
        return regionSidoRepository.findAllSidoSigungu().stream()
                .map(sido -> AllRegionDto.builder()
                        .sidoCd(sido.getSidoCd())
                        .sidoName(sido.getSidoName())
                        .sigunguList(
                                sido.getSigunguList().stream()
                                        .map(sigungu -> {
                                            List<AllRegionDto.DongDto> dongList = sigungu.getDongList().stream()
                                                    .map(dong -> new AllRegionDto.DongDto(dong.getDongCd(), dong.getDongName()))
                                                    .collect(Collectors.toList());
                                            return new AllRegionDto.SigunguDto(sigungu.getSigunguCd(), sigungu.getSigunguName(), dongList);
                                        })
                                        .collect(Collectors.toList())
                        )
                        .build()
                )
                .toList();
    }

}
