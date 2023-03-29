package com.rshu.schedule.subjects;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public Subject findSubject(String subjectName) {
        return subjectRepository.findByName(subjectName);
    }
}
