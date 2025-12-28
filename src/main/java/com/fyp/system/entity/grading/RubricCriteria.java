package com.fyp.system.entity.grading;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rubric_criteria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RubricCriteria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "rubric_id")
    private Rubric rubric;

    private String criteriaName;
    private Double maxMarks;
    private Double weightage;
}
