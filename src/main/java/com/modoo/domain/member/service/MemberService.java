package com.modoo.domain.member.service;

import com.modoo.domain.member.dto.MemberDto;
import com.modoo.domain.member.dto.request.MemberRequest;
import com.modoo.domain.member.entity.Member;
import com.modoo.domain.member.repository.MemberRepository;
import com.modoo.domain.metadata.entity.RegionDong;
import com.modoo.domain.metadata.repository.RegionDongRepository;
import com.modoo.global.constant.FileType;
import com.modoo.global.entity.ImageFile;
import com.modoo.global.service.FileService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final FileService fileService;
    private final RegionDongRepository regionDongRepository;

    /**
     * 회원 가입
     * @param request
     * @return
     */
    @Transactional
    public Long create(MemberRequest request) {
        request.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));

        RegionDong region = regionDongRepository.findById(Long.parseLong(request.getRegionCd()))
                .orElseThrow(() -> new RuntimeException("지역 코드가 올바르지 않습니다."));

        Member member = Member.of(request, region);

        // 파일 저장
        if(request.getProfile() != null) {
            ImageFile imageFile = fileService.singleFileUpload(request.getProfile(), FileType.PROFILE);
            log.info("/// [member file upload success] :: file_cd -> {} ///", imageFile.getFileCd());
            member.attachProfile(imageFile);
        }

        Member saved = memberRepository.save(member);
        return saved.getMemberCd();
    }

    /**
     * id 중복 체크
     * @param memberId
     * @return
     */
    public boolean checkIdDuplicate(String memberId) {
       Optional<Member> member = memberRepository.findByMemberId(memberId);
       if(member.isPresent()) {
           return true;
       }
       return false;
    }

    public MemberDto findByMemberCd(Long memberCd) {
        Member member = memberRepository.findById(memberCd)
                .orElseThrow(() -> new RuntimeException("회원이 존재하지 않습니다."));
        return MemberDto.fromEntity(member);
    }
}
