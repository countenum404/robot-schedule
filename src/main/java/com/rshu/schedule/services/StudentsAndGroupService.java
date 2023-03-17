package com.rshu.schedule.services;

import com.rshu.schedule.dto.GroupStudentDto;
import com.rshu.schedule.entities.Student;
import com.rshu.schedule.entities.StudyGroup;
import com.rshu.schedule.exceptions.EntityNotFoundException;
import com.rshu.schedule.exceptions.StudentNotFoundException;
import com.rshu.schedule.exceptions.StudyGroupNotFoundException;
import com.rshu.schedule.repos.GroupRepository;
import com.rshu.schedule.repos.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StudentsAndGroupService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GroupRepository groupRepository;

    public boolean createStudent(String firstName, String secondName, String surname){
        Student student = new Student(firstName, secondName, surname);
        studentRepository.save(student);
        return true;
    }

    public Collection<Student> getStudents(){
        return studentRepository.findAll();
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
        Student student = studentDto.getStudent();
        Student existingStudent = findStudent(student.getFirstname(), student.getLastname(), student.getSurname());
        StudyGroup group = groupRepository.findByName(studentDto.getGroup());
        if (group == null){
            throw new StudyGroupNotFoundException("");
        }
        existingStudent.setGroup(group);
        studentRepository.save(existingStudent);
        return true;
    }

    public boolean deleteStudent(Student student) throws EntityNotFoundException  {
        Student foundStudent = this.findStudent(student.getFirstname(), student.getLastname(), student.getSurname());
        studentRepository.delete(foundStudent);
        return true;
    }

    public Student findStudent(String firstname, String lastname, String surname) throws StudentNotFoundException {
        Student student = studentRepository.findStudent(firstname, lastname, surname);
        if (student == null){
            throw new StudentNotFoundException("");
        }
        return studentRepository.findStudent(firstname, lastname, surname);
    }
}
