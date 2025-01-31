package com.gelinski.superquiz.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_phase_one_question")
public class PhaseOneQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_theme")
    private Theme theme;

    @Column(name = "word")
    private String word;

    @ManyToOne
    @JoinColumn(name = "id_color")
    private Color color;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
}