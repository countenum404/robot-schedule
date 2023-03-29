package com.rshu.schedule.schedule;

import com.rshu.schedule.services.StudentsAndGroupService;
import com.rshu.schedule.services.TeacherService;
import com.rshu.schedule.study.group.StudyGroup;
import com.rshu.schedule.subjects.Subject;
import com.rshu.schedule.subjects.SubjectService;
import com.rshu.schedule.user.TeacherDTO;
import com.rshu.schedule.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepo scheduleRepo;
    private final StudentsAndGroupService studentsAndGroupService;
    private final TeacherService teacherService;

    private final SubjectService subjectService;

    public List<ScheduleDto> allRecords() {
        return scheduleRepo.findAll()
                .stream()
                .map(
                        scheduleRecord -> {
                            User u = scheduleRecord.getTeacher().get(0);
                            return ScheduleDto
                                    .builder()
                                    .teacher(TeacherDTO.builder()
                                            .firstname(u.getFirstname())
                                            .lastname(u.getLastname())
                                            .surname(u.getSurname())
                                            .build())
                                    .group(scheduleRecord.getStudyGroup().get(0).getName())
                                    .subject(scheduleRecord.getSubject().get(0).getName())
                                    .build();
                        }
                )
                .collect(Collectors.toList());
    }

    public Boolean createScheduleRecord(ScheduleDto schedule) {
        try {
            StudyGroup group = studentsAndGroupService.findGroup(schedule.getGroup());
            TeacherDTO teacherDTO = schedule.getTeacher();
            User teacher = teacherService.findTeacher(
                    teacherDTO.getFirstname(),
                    teacherDTO.getLastname(),
                    teacherDTO.getSurname()
            );
            Subject subject = subjectService.findSubject(schedule.getSubject());
            ScheduleRecord record = ScheduleRecord
                    .builder()
                    .subject(Arrays.asList(subject))
                    .teacher(Arrays.asList(teacher))
                    .studyGroup(Arrays.asList(group))
                    .build();
            scheduleRepo.save(record);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
