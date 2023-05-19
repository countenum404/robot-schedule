package com.rshu.schedule.schedule;

import com.rshu.schedule.user.TeacherDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDto {
    Long teacherId;
    TeacherDTO teacher;
    String group;
    String subject;
    TimeRange timeRange;
    Day dayRange;

    @Override
    public String toString() {
        return "ScheduleDto{" +
                "teacherId=" + teacherId +
                ", teacher=" + teacher +
                ", group='" + group + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
