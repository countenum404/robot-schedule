package com.rshu.schedule.study.group;

import com.rshu.schedule.user.User;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupStudentDto {
    private String login;
    private String firstname;
    private String lastname;
    private String group;
}
