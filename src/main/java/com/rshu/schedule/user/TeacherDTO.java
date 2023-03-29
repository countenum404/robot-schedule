package com.rshu.schedule.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeacherDTO {
    private String firstname;
    private String lastname;
    private String surname;
}
