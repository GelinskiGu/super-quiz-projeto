package com.gelinski.superquiz.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "tb_student")
@Getter
@Setter
@JsonIgnoreProperties
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStudent;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_group")
    private Group group;
}
