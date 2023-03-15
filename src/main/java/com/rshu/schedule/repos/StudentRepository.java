package com.rshu.schedule.repos;

import com.rshu.schedule.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student, Long> {
    public Student findStudentByfirstname(String firstname);

    @Query("select s from #{#entityName} s where s.firstname = ?1 AND s.lastname = ?2 AND s.surname = ?3")
    public Student findStudent(String firstname,
                               String lastname,
                               String surname);
}
