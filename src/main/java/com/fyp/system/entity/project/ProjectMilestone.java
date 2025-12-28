package com.fyp.system.entity.project;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "project_milestones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMilestone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    private String milestoneName;
    private LocalDate targetDate;
    private String status;
}
