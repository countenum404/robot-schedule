package com.rshu.schedule.services;

import com.rshu.schedule.study.group.GroupStudentDto;
import com.rshu.schedule.user.User;
import com.rshu.schedule.study.group.StudyGroup;
import com.rshu.schedule.exceptions.EntityNotFoundException;
import com.rshu.schedule.user.StudentNotFoundException;
import com.rshu.schedule.study.group.StudyGroupNotFoundException;
import com.rshu.schedule.study.group.GroupRepository;
import com.rshu.schedule.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StudentsAndGroupService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

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
        User user = studentDto.getUser();
        User existingUser = findStudent(user.getFirstname(), user.getLastname(), user.getSurname());
        StudyGroup group = groupRepository.findByName(studentDto.getGroup());
        if (group == null){
            throw new StudyGroupNotFoundException("");
        }
        existingUser.setGroup(group);
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
        if (user == null){
            throw new StudentNotFoundException("");
        }
        return userRepository.findStudent(firstname, lastname, surname);
    }
}
