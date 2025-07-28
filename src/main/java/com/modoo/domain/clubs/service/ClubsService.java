package com.modoo.domain.clubs.service;

import com.modoo.domain.clubs.dto.request.ClubsFileRequest;
import com.modoo.domain.clubs.dto.request.ClubsRequest;
import com.modoo.domain.clubs.entity.ClubsFile;
import com.modoo.domain.clubs.repository.ClubsFileRepository;
import com.modoo.domain.clubs.repository.ClubsRepository;
import com.modoo.domain.metadata.entity.Metadata;
import com.modoo.domain.metadata.entity.RegionDong;
import com.modoo.domain.metadata.repository.MetadataRepository;
import com.modoo.domain.metadata.repository.RegionDongRepository;
import com.modoo.global.constant.FileType;
import com.modoo.global.entity.ImageFile;
import com.modoo.global.service.FileService;
import com.modoo.global.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.modoo.domain.clubs.entity.Clubs;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ClubsService {

    private final ClubsRepository clubsRepository;
    private final MetadataRepository metadataRepository;
    private final RegionDongRepository regionDongRepository;
    private final FileUtil fileUtil;
    private final FileService fileService;
    private final ClubsFileRepository clubsFileRepository;

    /**
     * 모임 생성
     */
    public Map<String, Object> create(ClubsRequest clubsRequest, MultipartFile[] files, MultipartFile mainFile) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            // 지역 조회
            RegionDong region = regionDongRepository.findById(Long.valueOf(clubsRequest.getRegionCd()))
                    .orElseThrow(() -> new RuntimeException("지역 코드가 올바르지 않습니다."));
            // 카테고리 조회
            Metadata category = metadataRepository.findById(Long.valueOf(clubsRequest.getCategoryCd()))
                    .orElseThrow(() -> new RuntimeException("카테고리 코드가 올바르지 않습니다."));
            ImageFile imageFile = fileService.singleFileUpload(mainFile, FileType.CLUBS);
            // 모임 저장
            Clubs clubs = clubsRepository.save(Clubs.of(clubsRequest, region, category, imageFile));
            resultMap.put("success", true);
            resultMap.put("message", "모임이 성공적으로 저장되었습니다.");
            resultMap.put("id", clubs.getClubsCd());

            for (MultipartFile file : files) {
                ImageFile subImageFile = fileService.singleFileUpload(file, FileType.CLUBS);
                clubsFileRepository.save(ClubsFile.of(clubs, subImageFile));
            }
        } catch (Exception e) {
            resultMap.put("success", false);
            resultMap.put("message", "모임 저장 중 오류가 발생했습니다.");
            resultMap.put("error", e.getMessage());
        }

        return resultMap;
    }
}
