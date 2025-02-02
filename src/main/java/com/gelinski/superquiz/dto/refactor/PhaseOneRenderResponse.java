package com.gelinski.superquiz.dto.refactor;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class PhaseOneRenderResponse extends RenderPhasesBaseResponse {
    private List<String> colors = new ArrayList<>();
    private List<PhaseOneWordsDTO> phaseOneWordsDTOS = new ArrayList<>();
    private Integer totalDifficulties;
}
