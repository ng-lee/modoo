package com.modoo.domain.member.service;

import com.modoo.domain.member.dto.request.MemberRequest;
import com.modoo.domain.member.dto.response.MemberResponse;
import com.modoo.domain.member.entity.Member;
import com.modoo.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResponse create(MemberRequest request) {
        return MemberResponse.fromEntity(
                memberRepository.save(Member.of(request))
        );
    }
}
