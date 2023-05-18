package com.rshu.schedule.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rshu.schedule.schedule.ScheduleRecord;
import com.rshu.schedule.security.token.Token;
import com.rshu.schedule.study.group.StudyGroup;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "STUDY_ORG_USERS")
public class User implements UserDetails {
    @Id
    @SequenceGenerator(name = "usr_gen", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usr_gen")
    @JsonIgnore
    private Long id;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name = "Firstname")
    private String firstname;
    @Column(name = "Lastname")
    private String lastname;

    @Column(name = "Surname")
    private String surname;
    @Column(name="login", unique=true)
    private String login;
    private String password;

    @ManyToMany(mappedBy = "teacher", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private List<ScheduleRecord> records = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private List<Token> tokens;

    @ManyToOne
    @JoinColumn(name = "study_group_id")
    @JsonIgnore
    private StudyGroup group;

    public User(String firstname, String lastname, String surname) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.surname = surname;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", surname='" + surname + '\'' +
                ", group=" + group +
                '}';
    }
}
