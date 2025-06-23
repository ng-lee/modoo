package com.modoo.domain.member.controller;

import com.modoo.domain.member.dto.request.LoginRequest;
import com.modoo.domain.member.service.AuthService;
import com.modoo.global.dto.jwt.AuthenticationDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "/member/login";
    }

    @PostMapping("/login")
    public String login(@Valid LoginRequest loginRequest,
                        BindingResult bindingResult,
                        Model model,
                        HttpServletResponse response
    ) {
        model.addAttribute("loginRequest", loginRequest);

        if (bindingResult.hasErrors()) {
            return "/member/login";
        }

        try {
            AuthenticationDto authenticationDto = authService.login(loginRequest);

            //쿠키 생성
            Cookie accessTokenCookie = new Cookie("access-token", authenticationDto.getAccessToken());
            accessTokenCookie.setHttpOnly(true); // JS 접근 불가
            accessTokenCookie.setMaxAge(60 * 60);

            Cookie refreshTokenCookie = new Cookie("refresh-token", authenticationDto.getRefreshToken());
            refreshTokenCookie.setHttpOnly(true); // JS 접근 불가
            refreshTokenCookie.setMaxAge(60 * 60 * 24 * 7);

            response.addCookie(accessTokenCookie);
            response.addCookie(refreshTokenCookie);
        } catch (Exception e) {
            log.error("exception", e);
            model.addAttribute("errorMsg", e.getMessage());
            return "/member/login";
        }

        return "redirect:/";
    }

    @GetMapping("/join")
    public String joinPage() {
        return "/member/join";
    }

    @PostMapping("/join")
    public String join() {
        return "redirect:/login";
    }
}
