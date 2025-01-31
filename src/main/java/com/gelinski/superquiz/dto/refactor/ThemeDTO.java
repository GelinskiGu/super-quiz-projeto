package com.gelinski.superquiz.dto.refactor;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ThemeDTO implements Serializable {
    Integer id;
    String name;
}