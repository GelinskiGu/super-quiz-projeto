package com.gelinski.superquiz.service;

import com.gelinski.superquiz.dto.refactor.CreateThemeRequest;
import com.gelinski.superquiz.dto.refactor.ThemeDTO;
import com.gelinski.superquiz.model.Group;
import com.gelinski.superquiz.model.Student;
import com.gelinski.superquiz.model.Theme;
import com.gelinski.superquiz.repository.GroupRepository;
import com.gelinski.superquiz.repository.StudentRepository;
import com.gelinski.superquiz.repository.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ThemeService {
    private final ThemeRepository themeRepository;
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;

    public List<ThemeDTO> getThemes(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow();

        List<Theme> allByUserId = themeRepository.findAllByGroupId(student.getGroup().getIdGroup());
        return allByUserId.stream().map(theme -> new ThemeDTO(theme.getId(), theme.getName())).toList();
    }

    public void createTheme(CreateThemeRequest themeRequest) {
        Group group = groupRepository.findById(themeRequest.getGroupId()).orElseThrow();

        Theme theme = new Theme();
        theme.setName(themeRequest.getName());
        theme.setGroup(group);
        themeRepository.save(theme);
    }
}
