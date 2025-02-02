package com.gelinski.superquiz.dto.refactor;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PhaseTwoExerciseDTO {
    private Long phaseTwoExerciseId;
    private List<String> statements = new ArrayList<>();
    private List<AnswerDTO> answers = new ArrayList<>();
    private Integer correctAnswerId;
}
