package com.rshu.schedule.subjects;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rshu.schedule.schedule.ScheduleRecord;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Name")
    private String name;

    @ManyToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ScheduleRecord> records = new ArrayList<>();

    @Override
    public String toString() {
        return "Subject{" +
                "subjectName='" + name + '\'' +
                '}';
    }

    public Subject(String name) {
        this.name = name;
    }
}
