package com.rshu.schedule.schedule;

import com.rshu.schedule.study.group.StudyGroup;
import com.rshu.schedule.subjects.Subject;
import com.rshu.schedule.user.TeacherDTO;
import com.rshu.schedule.user.User;
import lombok.*;

import java.util.Collection;


@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class ScheduleDto {
    TeacherDTO teacher;
    String group;
    String subject;

    @Override
    public String toString() {
        return "ScheduleDto{" +
                "teachers=" + teacher +
                ", groups=" + group +
                ", subjects=" + subject +
                '}';
    }
}
