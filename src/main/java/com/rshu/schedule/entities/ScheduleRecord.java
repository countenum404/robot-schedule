package com.rshu.schedule.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@NoArgsConstructor
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
    @JoinColumn(name = "")
    private Collection<StudyGroup> studyGroup;
}
