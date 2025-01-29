package com.gelinski.superquiz.service;

import com.gelinski.superquiz.dto.StudentDTO;
import com.gelinski.superquiz.mapper.StudentMapper;
import com.gelinski.superquiz.model.Group;
import com.gelinski.superquiz.model.Student;
import com.gelinski.superquiz.repository.GroupRepository;
import com.gelinski.superquiz.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final GroupRepository groupRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Transactional
    public Student save(StudentDTO student) {
        Student entity = studentMapper.toEntity(student);
        Group group = groupRepository.findById(student.getGroup()).orElseThrow();
        entity.setGroup(group);
        return studentRepository.save(entity);
    }

    @Transactional
    public Student edit(StudentDTO student) {
        Student entity = studentRepository.findById(student.getId()).orElseThrow();
        entity.setName(student.getName());
        entity.setGroup(groupRepository.findById(student.getGroup()).orElseThrow());
        return studentRepository.save(entity);
    }
}
