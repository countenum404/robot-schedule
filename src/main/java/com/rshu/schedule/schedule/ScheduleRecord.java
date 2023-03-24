package com.rshu.schedule.schedule;


import com.rshu.schedule.subjects.Subject;
import com.rshu.schedule.study.group.StudyGroup;
import com.rshu.schedule.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class ScheduleRecord {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter
    @Setter
    @ManyToMany
    @JoinColumn(name = "schedule_subjects")
    @NonNull
    private Collection<Subject> subjects;

    @Getter
    @Setter
    @ManyToMany
    @JoinColumn(name = "schedule_group")
    @NonNull
    private Collection<StudyGroup> studyGroup;

    @Getter
    @Setter
    @ManyToMany
    @JoinColumn(name = "schedule_teacher")
    @NonNull
    private Collection<User> teachers;
}