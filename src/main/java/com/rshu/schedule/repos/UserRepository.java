package com.rshu.schedule.repos;

import com.rshu.schedule.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findStudentByfirstname(String firstname);

    public Optional<User> findByLogin(String login);

    @Query("select s from #{#entityName} s where s.firstname = ?1 AND s.lastname = ?2 AND s.surname = ?3")
    public User findStudent(String firstname,
                            String lastname,
                            String surname);
}
