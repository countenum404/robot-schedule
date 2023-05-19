package com.rshu.schedule.schedule;


import com.rshu.schedule.subjects.Subject;
import com.rshu.schedule.study.group.StudyGroup;
import com.rshu.schedule.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Subject> subject;

    @NonNull
    @ManyToMany(cascade = CascadeType.ALL)
    private List<StudyGroup> studyGroups = new ArrayList<>();

    @NonNull
    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> teacher = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Day dayOfWeek;

    @Enumerated(EnumType.STRING)
    private TimeRange timeOfDay;
}




