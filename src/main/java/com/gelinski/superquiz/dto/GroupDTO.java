package com.gelinski.superquiz.dto;

import lombok.Data;

@Data
public class GroupDTO {
    private Long id;
    private String name;
    private String acronym;
    private Long teacher;
}
