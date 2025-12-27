package com.fyp.system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "evaluations")
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double marks;

    @Column(columnDefinition = "TEXT")
    private String comments;

    @Column(columnDefinition = "TEXT")
    private String rubricCriteria;

    @OneToOne
    @JoinColumn(name = "document_id")
    private Document document;

    @ManyToOne
    @JoinColumn(name = "evaluator_id")
    private User evaluator;
}
