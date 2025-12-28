package com.fyp.system.entity.grading;

import com.fyp.system.entity.project.Project;
import com.fyp.system.entity.academic.Evaluator;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "evaluations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "evaluator_id")
    private Evaluator evaluator;

    private Double totalMarksObtained;
    private String grade;
    private String evaluationStatus;
}
