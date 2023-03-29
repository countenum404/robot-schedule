package com.rshu.schedule.schedule;


import com.rshu.schedule.subjects.Subject;
import com.rshu.schedule.study.group.StudyGroup;
import com.rshu.schedule.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
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

    @ManyToMany
    @JoinColumn(name = "schedule_subjects")
    @NonNull
    private List<Subject> subject;

    @ManyToMany
    @JoinColumn(name = "schedule_group")
    @NonNull
    private List<StudyGroup> studyGroup;


    @ManyToMany
    @JoinColumn(name = "schedule_teacher")
    @NonNull
    private List<User> teacher;
}




