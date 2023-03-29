package com.rshu.schedule.services;

import com.rshu.schedule.user.Role;
import com.rshu.schedule.user.User;
import com.rshu.schedule.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TeacherService {
    private final UserRepository userRepository;

    public User findTeacher(String firstname,
                            String lastname,
                            String surname) {
        User teacher = userRepository.findUser(firstname,
                lastname, surname, Role.TEACHER);
        return teacher;
    }
}
