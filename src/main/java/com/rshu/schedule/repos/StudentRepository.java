package com.rshu.schedule.repos;

import com.rshu.schedule.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    public Student findStudentByfirstname(String firstname);
}
