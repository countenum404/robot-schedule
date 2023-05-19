package com.rshu.schedule.schedule;

import com.rshu.schedule.study.group.StudyGroup;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DateAndTimeService {
    private final ScheduleRepo recordsRepository;

    public ScheduleRecord addTimeAndDate(ScheduleDto scheduleDto, StudyGroup group) {
        TimeRange range = scheduleDto.getTimeRange();
        Day day = scheduleDto.getDayRange();
        // ScheduleRecord record = recordsRepository.findByStudyGroupId(group.getId());
        return ScheduleRecord.builder().build();
    }

}
