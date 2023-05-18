package com.rshu.schedule.services;

import com.rshu.schedule.user.Role;
import com.rshu.schedule.user.User;
import com.rshu.schedule.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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

    public Optional<User> findTeacher(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getAllTeachers() {
        return userRepository.findAllByRole(Role.TEACHER);
    }
}
