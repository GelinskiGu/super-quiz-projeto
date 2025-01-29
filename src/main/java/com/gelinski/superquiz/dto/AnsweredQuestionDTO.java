package com.gelinski.superquiz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AnsweredQuestionDTO {
    private String answer;
    private String correctAnswer;
    private Long seconds;
}
