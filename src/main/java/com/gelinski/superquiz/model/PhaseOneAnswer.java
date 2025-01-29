package com.gelinski.superquiz.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "tb_phase_one_answer")
@Getter
@Setter
public class PhaseOneAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String answer;

    @Column
    private String correctAnswer;

    @Column
    private boolean isCorrect;

    @Column
    private Long seconds;

    @ManyToOne
    @JoinColumn(name = "id_student")
    private Student student;
}
