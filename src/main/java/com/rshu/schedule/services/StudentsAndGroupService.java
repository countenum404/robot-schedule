package com.rshu.schedule.services;

import com.rshu.schedule.study.group.GroupStudentDto;
import com.rshu.schedule.user.User;
import com.rshu.schedule.study.group.StudyGroup;
import com.rshu.schedule.exceptions.EntityNotFoundException;
import com.rshu.schedule.user.StudentNotFoundException;
import com.rshu.schedule.study.group.StudyGroupNotFoundException;
import com.rshu.schedule.study.group.GroupRepository;
import com.rshu.schedule.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentsAndGroupService {
    private final UserDetailsService userDetailsService;

    private final UserRepository userRepository;

    private final GroupRepository groupRepository;

    public boolean createStudent(String firstName, String secondName, String surname){
        User user = new User(firstName, secondName, surname);
        userRepository.save(user);
        return true;
    }

    public Collection<User> getStudents(){
        return userRepository.findAll();
    }

    public Collection<StudyGroup> getGroups(){
        return groupRepository.findAll();
    }

    public boolean createGroup(String groupName){
        StudyGroup studyGroup = new StudyGroup();
        studyGroup.setName(groupName);
        groupRepository.save(studyGroup);
        return true;
    }

    public boolean mapStudentAndGroup(GroupStudentDto studentDto) throws EntityNotFoundException {
        User existingUser = this.userRepository.findStudent(
                studentDto.getFirstname(),
                studentDto.getLastname(),
                studentDto.getLogin()
        );
        var group = groupRepository.findByName(studentDto.getGroup());
        if (group.isEmpty()){
            throw new StudyGroupNotFoundException("");
        }
        existingUser.setGroup(group.get());
        userRepository.save(existingUser);
        return true;
    }

    public boolean deleteStudent(User user) throws EntityNotFoundException  {
        User foundUser = this.findStudent(user.getFirstname(), user.getLastname(), user.getSurname());
        userRepository.delete(foundUser);
        return true;
    }

    public User findStudent(String firstname, String lastname, String surname) throws StudentNotFoundException {
        User user = userRepository.findStudent(firstname, lastname, surname);
        if (user == null) {
            throw new StudentNotFoundException("");
        }
        return userRepository.findStudent(firstname, lastname, surname);
    }

    public StudyGroup findGroup(String name) {
        StudyGroup group = groupRepository.findByName(name).orElseThrow(() -> {
            new StudyGroupNotFoundException("");
            return null;
        });
        return group;
    }
}
