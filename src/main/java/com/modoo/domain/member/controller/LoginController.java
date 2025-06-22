package com.modoo.domain.member.controller;

import com.modoo.domain.member.dto.request.LoginRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "/member/login";
    }

    @PostMapping("/login")
    public String login(@Valid LoginRequest loginRequest,
                        BindingResult bindingResult,
                        Model model
    ) {
        model.addAttribute("loginRequest", loginRequest);

        if (bindingResult.hasErrors()) {
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
