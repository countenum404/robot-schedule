package com.rshu.schedule.services;

import com.rshu.schedule.entities.Teacher;
import com.rshu.schedule.repos.TeacherRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
@NoArgsConstructor
public class TeacherService {

    private TeacherRepository teacherRepository;

    public TeacherService(@Autowired TeacherRepository teacherRepository){

    }

    public Collection<Teacher> findAllTeachers(){
        return teacherRepository.findAll();
    }

    public Boolean addTeacher(Teacher teacher){
        teacherRepository.save(teacher);
        return true;
    }
}
