package com.rshu.schedule.repos;

import com.rshu.schedule.entities.ScheduleRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepo extends JpaRepository<ScheduleRecord, Long> {
}
