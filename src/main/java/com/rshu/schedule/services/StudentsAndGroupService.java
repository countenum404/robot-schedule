package com.rshu.schedule.services;

import com.rshu.schedule.study.group.GroupStudentDto;
import com.rshu.schedule.user.Role;
import com.rshu.schedule.user.User;
import com.rshu.schedule.study.group.StudyGroup;
import com.rshu.schedule.exceptions.EntityNotFoundException;
import com.rshu.schedule.user.StudentNotFoundException;
import com.rshu.schedule.study.group.StudyGroupNotFoundException;
import com.rshu.schedule.study.group.GroupRepository;
import com.rshu.schedule.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
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

    public Page<StudyGroup> getGroups(Pageable pageable){
        return groupRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
    }

    public List<StudyGroup> getGroups(){
        return groupRepository.findAll();
    }

    public boolean createGroup(String groupName){
        groupRepository.save(StudyGroup.builder().name(groupName).build());
        return true;
    }

    public boolean mapStudentAndGroup(GroupStudentDto studentDto) throws EntityNotFoundException {
        User existingUser = this.userRepository.findUser(
                studentDto.getFirstname(),
                studentDto.getLastname(),
                studentDto.getLogin(),
                Role.STUDENT
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
        User user = userRepository.findUser(firstname, lastname, surname, Role.STUDENT);
        if (user == null) {
            throw new StudentNotFoundException("");
        }
        return userRepository.findUser(firstname, lastname, surname, Role.STUDENT);
    }

    public StudyGroup findGroup(String name) {
        StudyGroup group = groupRepository.findByName(name).orElseThrow(() -> {
            new StudyGroupNotFoundException("");
            return null;
        });
        return group;
    }

    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }
}
