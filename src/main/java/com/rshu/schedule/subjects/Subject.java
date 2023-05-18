package com.rshu.schedule.subjects;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@NoArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Name")
    private String name;

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
