package com.gelinski.superquiz.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "tb_statement")
@Getter
@Setter
public class Statement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String statementDescription;

    @ManyToOne
    @JoinColumn(name = "id_phase_two_exercise")
    private PhaseTwoExercise phaseTwoExercise;
}
