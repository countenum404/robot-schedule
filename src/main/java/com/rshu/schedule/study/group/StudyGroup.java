package com.rshu.schedule.study.group;


import com.rshu.schedule.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
public class StudyGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "group")
    private Collection<User> users;

    @Override
    public String toString() {
        return "StudyGroup{" +
                "name='" + name + '\'' +
                '}';
    }
}
