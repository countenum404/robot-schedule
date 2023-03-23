package com.rshu.schedule.study.group;

import com.rshu.schedule.user.User;
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
    private User user;

}
