package com.gelinski.superquiz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PartsPhaseTwoDTO {
    private String part;
    private String tries;
    private String time;
}
