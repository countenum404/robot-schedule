package com.rshu.schedule.web;


import com.rshu.schedule.schedule.ScheduleDto;
import com.rshu.schedule.schedule.ScheduleService;
import com.rshu.schedule.services.StudentsAndGroupService;
import com.rshu.schedule.services.TeacherService;
import com.rshu.schedule.subjects.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.templateparser.text.TextTemplateParser;

@Controller
@RequestMapping("/admin/panel/schedule")
@AllArgsConstructor
public class ScheduleAdminController {

    private final ScheduleService scheduleService;
    private final StudentsAndGroupService studentsAndGroupService;
    private final SubjectService subjectService;
    private final TeacherService teacherService;

    @GetMapping
    public String getSchedulePage(Model model) {
        model.addAttribute("records", scheduleService.allRecords());
        return "schedule";
    }

    @GetMapping("/new")
    public String getNewRecordPage(Model model) {
        model.addAttribute("teachers", teacherService.getAllTeachers());
        model.addAttribute("groups", studentsAndGroupService.getGroups());
        model.addAttribute("subjects", subjectService.getAllSubjects());
        model.addAttribute("records", scheduleService.allRecords());
        return "new-record";
    }

    @PostMapping("/new")
    public String addNewRecord(@ModelAttribute("record") ScheduleDto scheduleDto) {
        System.out.println(scheduleDto);
        scheduleService.createScheduleRecord(scheduleDto);
        return "redirect:/admin/panel/schedule";
    }
}
