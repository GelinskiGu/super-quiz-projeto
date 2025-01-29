package com.gelinski.superquiz.mapper;

import com.gelinski.superquiz.dto.StudentDTO;
import com.gelinski.superquiz.model.Student;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface StudentMapper {
    @Mapping(target = "group", ignore = true)
    Student toEntity(StudentDTO studentDTO);
    @Mapping(target = "group", ignore = true)
    @Mapping(target = "id", ignore = true)
    StudentDTO toDto(Student student);
}