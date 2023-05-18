package com.rshu.schedule.subjects;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;
    public Subject findSubject(String subjectName) {
        return subjectRepository.findByName(subjectName);
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Page<Subject> getAllSubjects(Pageable pageable) {
        return subjectRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
    }

    public void createSubject(String name) {
        subjectRepository.save(new Subject(name));
    }
}
