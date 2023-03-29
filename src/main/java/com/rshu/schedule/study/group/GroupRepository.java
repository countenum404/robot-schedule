package com.rshu.schedule.study.group;

import com.rshu.schedule.study.group.StudyGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<StudyGroup, Long> {
    public Optional<StudyGroup> findByName(String groupName);
}
