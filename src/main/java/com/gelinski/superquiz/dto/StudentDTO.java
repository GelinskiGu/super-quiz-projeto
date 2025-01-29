package com.gelinski.superquiz.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class StudentDTO implements Serializable {
    private Long id;
    private String name;
    private Long group;
}