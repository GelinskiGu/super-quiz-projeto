package com.gelinski.superquiz.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "tb_question")
@Getter
@Setter
@JsonIgnoreProperties
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "is_correct_answer")
    private Boolean isCorrectAnswer;

    @ManyToOne
    @JoinColumn(name = "id_phase_two_exercise")
    private PhaseTwoExercise phaseTwoExercise;
}
