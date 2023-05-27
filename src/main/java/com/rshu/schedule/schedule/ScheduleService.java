package com.rshu.schedule.schedule;

import com.rshu.schedule.services.StudentsAndGroupService;
import com.rshu.schedule.services.TeacherService;
import com.rshu.schedule.study.group.StudyGroup;
import com.rshu.schedule.subjects.Subject;
import com.rshu.schedule.subjects.SubjectService;
import com.rshu.schedule.user.TeacherDTO;
import com.rshu.schedule.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
                                    .group(scheduleRecord.getStudyGroups().get(0).getName())
                                    .subject(scheduleRecord.getSubject().get(0).getName())
                                    .timeRange(scheduleRecord.getTimeOfDay())
                                    .dayRange(scheduleRecord.getDayOfWeek())
                                    .build();
                        }
                )
                .collect(Collectors.toList());
    }

    public Page<ScheduleRecord> allRecords(Pageable pageable) {
        return scheduleRepo.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
    }

    public Boolean createScheduleRecord(ScheduleDto schedule) {
        try {
            StudyGroup group = studentsAndGroupService.findGroup(schedule.getGroup());
            TeacherDTO teacherDTO = schedule.getTeacher();
            Long teacher_id = schedule.getTeacherId();
            User teacher;
            if (teacherDTO != null) {
                teacher = teacherService.findTeacher(
                        teacherDTO.getFirstname(),
                        teacherDTO.getLastname(),
                        teacherDTO.getSurname()
                );
            } else {
                teacher = teacherService.findTeacher(teacher_id).get();
            }
            Subject subject = subjectService.findSubject(schedule.getSubject());
            ScheduleRecord record = ScheduleRecord
                    .builder()
                    .subject(Arrays.asList(subject))
                    .teacher(Arrays.asList(teacher))
                    .studyGroups(Arrays.asList(group))
                    .dayOfWeek(schedule.getDayRange())
                    .timeOfDay(schedule.getTimeRange())
                    .build();
            scheduleRepo.save(record);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
