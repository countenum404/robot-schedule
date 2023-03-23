package com.rshu.schedule.study.group;

import com.rshu.schedule.study.group.StudyGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<StudyGroup, Long> {
    public StudyGroup findByName(String groupName);
}
