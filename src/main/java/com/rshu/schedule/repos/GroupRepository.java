package com.rshu.schedule.repos;

import com.rshu.schedule.entities.StudyGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<StudyGroup, Long> {
    public StudyGroup findByName(String groupName);
}
