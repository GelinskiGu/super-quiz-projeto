package com.gelinski.superquiz.dto.refactor;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PhaseOneWordsDTO {
    private Long phaseOneId;
    private String word;
    private String description;
    private String color;
}
