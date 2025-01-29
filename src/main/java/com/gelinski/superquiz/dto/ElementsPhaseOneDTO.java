package com.gelinski.superquiz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ElementsPhaseOneDTO {
    private String element;
    private int tries;
    private String time;
}
