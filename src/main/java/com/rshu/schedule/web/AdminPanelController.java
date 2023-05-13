package com.rshu.schedule.web;


import com.rshu.schedule.services.StudentsAndGroupService;
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
    private final StudentsAndGroupService studentsAndGroupService;

    @GetMapping("/users")
    public String users(Model model,
                        @PageableDefault(sort = { "lastname" }, direction = Sort.Direction.DESC, size = 30) Pageable pageable) {
        model.addAttribute("usersList", userService.getAllUsers(pageable));
        return "users";
    }

    @GetMapping("/groups")
    public String groups(Model model,
                         @PageableDefault(sort = { "lastname" }, direction = Sort.Direction.DESC, size = 1) Pageable pageable) {
        model.addAttribute("groups", studentsAndGroupService.getGroups(pageable));
        return "groups";
    }

    @GetMapping("/schedule")
    public String schedule() {
        return "schedule";
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
