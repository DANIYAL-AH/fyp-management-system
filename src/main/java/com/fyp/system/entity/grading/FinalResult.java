package com.fyp.system.entity.grading;

import com.fyp.system.entity.project.Project;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "final_results")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FinalResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project;

    private Double totalMarks;
    private String grade;
    private boolean isPassed;
    private boolean isReleased;
}
