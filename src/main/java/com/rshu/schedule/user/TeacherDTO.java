package com.rshu.schedule.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {
    private String firstname;
    private String lastname;
    private String surname;

    public TeacherDTO(User user) {
        firstname = user.getFirstname();
        lastname = user.getLastname();
        surname = user.getSurname();
    }
}
