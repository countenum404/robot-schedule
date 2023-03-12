package com.rshu.schedule.dto;

import com.rshu.schedule.entities.StudyGroup;
import com.rshu.schedule.entities.Subject;
import com.rshu.schedule.entities.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDto {

    @Getter
    @Setter
    Teacher teacher;

    @Getter
    @Setter
    StudyGroup group;

    @Getter
    @Setter
    Subject subject;
}
