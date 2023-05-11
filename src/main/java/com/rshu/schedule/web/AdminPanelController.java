package com.rshu.schedule.web;


import com.rshu.schedule.user.User;
import com.rshu.schedule.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/panel")
@AllArgsConstructor
public class AdminPanelController {

    private final UserService userService;

    @GetMapping("/users")
    public String users(Model model,
                        @PageableDefault(sort = { "lastname" }, direction = Sort.Direction.DESC, size = 1) Pageable pageable) {
        Page<User> pages = userService.getAllUsers(pageable);
        model.addAttribute("usersList", pages);
        return "users";
    }

    @GetMapping("/groups")
    public String groups() {
        return "groups";
    }


    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }

    @GetMapping()
    public String panel(Model model) {
        return "panel";
    }
}
