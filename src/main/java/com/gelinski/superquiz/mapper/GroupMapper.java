package com.gelinski.superquiz.mapper;

import com.gelinski.superquiz.dto.GroupDTO;
import com.gelinski.superquiz.model.Group;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface GroupMapper {
    @Mapping(target = "teacher", ignore = true)
    Group toEntity(GroupDTO groupDTO);
    @Mapping(target = "teacher", ignore = true)
    @Mapping(target = "id", ignore = true)
    GroupDTO toDto(Group group);
}