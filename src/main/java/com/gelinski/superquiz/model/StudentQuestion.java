package com.gelinski.superquiz.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "tb_student_question")
@Getter
@Setter
@JsonIgnoreProperties({"student", "question"})
public class StudentQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_student")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "id_answer")
    private Answer answer;

    @Column
    private boolean isCorrect;

    @Column
    private String word;

    @Column
    private String correctAnswer;

    @Column
    private Long seconds;
}
