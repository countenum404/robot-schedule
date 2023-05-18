package com.rshu.schedule.web;


import com.rshu.schedule.security.auth.AuthService;
import com.rshu.schedule.security.dto.RegisterRequest;
import com.rshu.schedule.services.StudentsAndGroupService;
import com.rshu.schedule.user.Role;
import com.rshu.schedule.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/panel")
@AllArgsConstructor
public class UserAdminController {

    private final UserService userService;
    private final AuthService authService;

    @GetMapping("/users")
    public String users(Model model,
                        @PageableDefault(sort = { "lastname" }, direction = Sort.Direction.DESC, size = 30) Pageable pageable) {
        model.addAttribute("usersList", userService.getAllUsers(pageable));
        return "users";
    }

    @GetMapping("/users/new")
    public String getNewUserPage(@ModelAttribute("request") RegisterRequest registerRequest) {
        return "new-user";
    }

    @PostMapping("/users/new")
    public String addNewUser(@ModelAttribute("request") RegisterRequest registerRequest) {
        System.out.println(registerRequest.getRole());
        authService.register(registerRequest);
        return "redirect:/admin/panel/users";
    }

    @GetMapping("/users/delete")
    public String deleteUser(@RequestParam long id) {
        System.out.println(id);
        userService.deleteUser(id);
        return "redirect:/admin/panel/users";
    }

    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }

    @GetMapping
    public String panel(Model model) {
        return "panel";
    }
}
