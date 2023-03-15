package com.rshu.schedule.services;

import com.rshu.schedule.entities.Student;
import com.rshu.schedule.entities.StudyGroup;
import com.rshu.schedule.exceptions.StudentNotFoundException;
import com.rshu.schedule.repos.GroupRepository;
import com.rshu.schedule.repos.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StudentsService {

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

    public boolean mapStudentAndGroup(Student student){
        Student existingStudent = studentRepository.findStudentByfirstname(student.getFirstname());
        StudyGroup group = groupRepository.findByName(student.getGroup().getName());
        existingStudent.setGroup(group);
        studentRepository.save(existingStudent);
        return true;
    }

    public boolean deleteStudent(Student student){
        studentRepository.delete(student);

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
