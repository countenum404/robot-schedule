package com.rshu.schedule.study.group;


import com.rshu.schedule.schedule.ScheduleRecord;
import com.rshu.schedule.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudyGroup {

    @Id
    @SequenceGenerator(name = "gr_gen",
            sequenceName = "group_generator",
            initialValue = 4, allocationSize = 20
            )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gr_gen")
    private long id;
    private String name;

    @ManyToMany(mappedBy = "studyGroups", cascade = CascadeType.ALL)
    private List<ScheduleRecord> scheduleRecords = new ArrayList<>();

    @OneToMany(mappedBy = "group")
    private List<User> users;

    @Override
    public String toString() {
        return "StudyGroup{" +
                "name='" + name + '\'' +
                '}';
    }
}
