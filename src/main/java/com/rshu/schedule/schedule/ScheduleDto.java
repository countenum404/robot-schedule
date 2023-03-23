package com.rshu.schedule.schedule;

import com.rshu.schedule.study.group.StudyGroup;
import com.rshu.schedule.subjects.Subject;
import com.rshu.schedule.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;


@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDto {

    @Getter
    @Setter
    Collection<User> teacher;

    @Getter
    @Setter
    Collection<StudyGroup> group;

    @Getter
    @Setter
    Collection<Subject> subject;

    @Override
    public String toString() {
        return "ScheduleDto{" +
                "teacher=" + (teacher.toArray())[0].toString() +
                ", group=" + (group.toArray())[0].toString() +
                ", subject=" + (subject.toArray())[0].toString() +
                '}';
    }
}
