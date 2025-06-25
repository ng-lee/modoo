package com.modoo.domain.clubs.controller;

import com.modoo.domain.member.dto.auth.MemberAuth;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ClubsController {

    @GetMapping("/clubs/create")
    public String create() {
        return "/clubs/create";
    }

}
