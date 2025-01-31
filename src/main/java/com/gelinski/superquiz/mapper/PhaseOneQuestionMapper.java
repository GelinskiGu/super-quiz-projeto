package com.gelinski.superquiz.mapper;

import com.gelinski.superquiz.dto.refactor.CreatePhaseOneRequest;
import com.gelinski.superquiz.model.Color;
import com.gelinski.superquiz.model.PhaseOneQuestion;
import com.gelinski.superquiz.model.Theme;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PhaseOneQuestionMapper {

    @Mapping(target = "id", ignore = true)
    PhaseOneQuestion toEntity(CreatePhaseOneRequest createPhaseOneRequest, Theme theme, Color color);
}