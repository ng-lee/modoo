package com.modoo.domain.clubs.service;

import com.modoo.domain.clubs.dto.request.ClubsRequest;
import com.modoo.domain.clubs.repository.ClubsRepository;
import com.modoo.domain.metadata.entity.Metadata;
import com.modoo.domain.metadata.entity.RegionDong;
import com.modoo.domain.metadata.repository.MetadataRepository;
import com.modoo.domain.metadata.repository.RegionDongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.modoo.domain.clubs.entity.Clubs;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ClubsService {

    private final ClubsRepository clubsRepository;
    private final MetadataRepository metadataRepository;
    private final RegionDongRepository regionDongRepository;

    /**
     * 모임 생성
     */
    public Map<String, Object> create(ClubsRequest clubsRequest) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            // 지역 조회
            RegionDong region = regionDongRepository.findById(Long.valueOf(clubsRequest.getRegionCd()))
                    .orElseThrow(() -> new RuntimeException("지역 코드가 올바르지 않습니다."));
            // 카테고리 조회
            Metadata category = metadataRepository.findById(Long.valueOf(clubsRequest.getCategoryCd()))
                    .orElseThrow(() -> new RuntimeException("카테고리 코드가 올바르지 않습니다."));
            
            // 모임 저장
            Clubs clubs = clubsRepository.save(Clubs.of(clubsRequest, region, category));
            resultMap.put("success", true);
            resultMap.put("message", "모임이 성공적으로 저장되었습니다.");
            resultMap.put("id", clubs.getClubsCd()); // 저장된 엔티티의 ID 반환 (옵션)
        } catch (Exception e) {
            resultMap.put("success", false);
            resultMap.put("message", "모임 저장 중 오류가 발생했습니다.");
            resultMap.put("error", e.getMessage());
        }

        return resultMap;
    }
}
