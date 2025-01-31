package com.gelinski.superquiz.dto.refactor;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class PhaseTwoRenderResponse extends RenderPhasesBaseResponse {
    private List<PhaseTwoExerciseDTO> phaseTwoExerciseDTOS = new ArrayList<>();
}
