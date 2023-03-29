package com.rshu.schedule.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByFirstname(String Firstname);

    Optional<User> findByLogin(String login);

    @Query("select s from #{#entityName} s where s.firstname = ?1 AND s.lastname = ?2 AND s.login = ?3 AND s.role = ?4")
    User findUser(String firstname,
                  String lastname,
                  String login,
                  Role role
    );
}
