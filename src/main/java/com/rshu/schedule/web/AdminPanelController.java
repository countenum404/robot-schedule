package com.rshu.schedule.web;


import com.rshu.schedule.security.auth.AuthService;
import com.rshu.schedule.security.dto.RegisterRequest;
import com.rshu.schedule.user.Role;
import com.rshu.schedule.user.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminPanelController {





    @GetMapping("/panel")
    public String panel(Model model, User userDetails) {
        System.out.println(userDetails.getUsername());
        model.addAttribute("name", userDetails.getUsername());
        return "panel";
    }
}
