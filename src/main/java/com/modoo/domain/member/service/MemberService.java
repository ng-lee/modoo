package com.modoo.domain.member.service;

import com.modoo.domain.member.dto.request.MemberRequest;
import com.modoo.domain.member.entity.Member;
import com.modoo.domain.member.repository.MemberRepository;
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

    /**
     * 회원 가입
     * @param request
     * @return
     */
    @Transactional
    public Long create(MemberRequest request) {
        request.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        Member member = Member.of(request);

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
}
