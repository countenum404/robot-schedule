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
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepo scheduleRepo;
    private final StudentsAndGroupService studentsAndGroupService;
    private final TeacherService teacherService;

    private final SubjectService subjectService;

    public List<ScheduleRecord> allRecords() {
        return scheduleRepo.findAll();
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
                    .subject(subject)
                    .teacher(teacher)
                    .studyGroup(group)
                    .build();
            scheduleRepo.save(record);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
