package com.rshu.schedule.schedule;


import com.rshu.schedule.subjects.Subject;
import com.rshu.schedule.study.group.StudyGroup;
import com.rshu.schedule.user.User;
import jakarta.persistence.*;
import lombok.*;

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
    private Subject subject;

    @ManyToMany
    @JoinColumn(name = "schedule_group")
    @NonNull
    private StudyGroup studyGroup;


    @ManyToMany
    @JoinColumn(name = "schedule_teacher")
    @NonNull
    private User teacher;
}




