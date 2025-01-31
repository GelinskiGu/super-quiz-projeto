package com.gelinski.superquiz.dto.refactor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatePhaseTwoRequest {
    private Integer idTheme;
    private List<String> statements;
    private String word;
    private List<String> answers;
    private String correctAnswer;
}
