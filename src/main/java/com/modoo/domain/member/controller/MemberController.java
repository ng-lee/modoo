package com.modoo.domain.member.controller;

import com.modoo.domain.member.dto.request.MemberRequest;
import com.modoo.domain.member.dto.response.MemberResponse;
import com.modoo.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member")
    public void create(@RequestBody MemberRequest request) {
       MemberResponse memberResponse = memberService.create(request);
       log.info("MemberResponse : {}", memberResponse);
    }

}
