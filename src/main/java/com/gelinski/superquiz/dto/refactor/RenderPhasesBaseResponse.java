package com.gelinski.superquiz.dto.refactor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RenderPhasesBaseResponse {
}
