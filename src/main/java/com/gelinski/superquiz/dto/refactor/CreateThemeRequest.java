package com.gelinski.superquiz.dto.refactor;

import lombok.Data;

@Data
public class CreateThemeRequest {
    private String name;
    private Long groupId;
}
