package com.rshu.schedule.web;

import com.rshu.schedule.services.StudentsAndGroupService;
import com.rshu.schedule.study.group.StudyGroup;
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
public class StudyGroupAdminController {

    private final StudentsAndGroupService studentsAndGroupService;

    @GetMapping("/groups")
    public String groups(Model model,
                         @PageableDefault(sort = { "lastname" }, direction = Sort.Direction.DESC, size = 1) Pageable pageable) {
        model.addAttribute("groups", studentsAndGroupService.getGroups(pageable));
        return "groups";
    }

    @GetMapping("/groups/new")
    public String addGroupPage (Model model) {
        model.addAttribute("create-group", new String());
        return "new-group";
    }

    @PostMapping("/groups/new")
    public String addGroup (@RequestParam(value = "create-group") String studyGroupName) {
        studentsAndGroupService.createGroup(studyGroupName);
        return "redirect:/admin/panel/groups";
    }

    @GetMapping("/groups/delete")
    public String deleteGroup(@RequestParam long id) {
        studentsAndGroupService.deleteGroup(id);
        return "redirect:/admin/panel/groups";
    }
}
