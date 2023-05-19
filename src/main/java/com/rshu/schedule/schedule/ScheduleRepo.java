package com.rshu.schedule.schedule;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepo extends JpaRepository<ScheduleRecord, Long> {
    ScheduleRecord findByStudyGroupId(Long id);
}
