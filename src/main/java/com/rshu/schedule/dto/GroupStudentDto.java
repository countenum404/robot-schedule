package com.rshu.schedule.dto;

import com.rshu.schedule.entities.Student;
import com.rshu.schedule.entities.StudyGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class GroupStudentDto {
    @Getter
    @Setter
    private String group;
    @Getter
    @Setter
    private Student student;

}