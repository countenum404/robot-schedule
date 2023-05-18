package com.rshu.schedule.web;

import com.rshu.schedule.subjects.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/panel/subjects")
@AllArgsConstructor
public class SubjectsController {

    private final SubjectService subjectService;

    @GetMapping
    public String getSubjects(Model model,
                              @PageableDefault(direction = Sort.Direction.DESC, size = 30) Pageable pageable) {
        model.addAttribute("subjects", subjectService.getAllSubjects(pageable));
        return "subjects";
    }

    @GetMapping("/new")
    public String addGroupPage (Model model) {
        model.addAttribute("create-subject", new String());
        return "new-subject";
    }

    @PostMapping("/new")
    public String addSubject(@RequestParam String subjectName) {
        subjectService.createSubject(subjectName);
        return "redirect:/admin/panel/subjects";
    }
}
