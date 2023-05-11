package com.rshu.schedule.web;

import com.rshu.schedule.security.auth.AuthService;
import com.rshu.schedule.security.dto.AuthenticationRequest;
import com.rshu.schedule.security.dto.RegisterRequest;
import com.rshu.schedule.user.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("admin")
@AllArgsConstructor
public class AuthAdminController {
    private final AuthService authService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("auth", new AuthenticationRequest());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("auth") AuthenticationRequest auth, Model model) {
        model.addAttribute("jwt", authService.authenticate(auth).getToken());
        return "registred";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("request", new RegisterRequest());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("request") RegisterRequest registerRequest, Model model) {
        registerRequest.setRole(Role.ADMIN);
        model.addAttribute("jwt", authService.register(registerRequest).getToken());
        return "registred";
    }
}
