package com.gelinski.superquiz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReportDTO {
    private String studentName;
    private PhaseOneReportDTO phaseOne;
    private PhaseTwoReportDTO phaseTwo;
}
