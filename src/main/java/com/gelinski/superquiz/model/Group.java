package com.gelinski.superquiz.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity(name = "tb_group")
@Getter
@Setter
public class Group implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGroup;

    @Column
    private String name;

    @Column
    private String acronym;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User teacher;
}
