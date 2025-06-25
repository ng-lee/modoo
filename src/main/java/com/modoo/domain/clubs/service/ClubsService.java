package com.modoo.domain.clubs.service;

import com.modoo.domain.clubs.dto.request.ClubsRequest;
import com.modoo.domain.clubs.repository.ClubsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.modoo.domain.clubs.entity.Clubs;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ClubsService {

    private final ClubsRepository clubsRepository;

    /**
     * 모임 생성
     */
    public Map<String, Object> create(ClubsRequest clubsRequest) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            Clubs clubs = clubsRepository.save(Clubs.of(clubsRequest));
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
