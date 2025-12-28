package com.fyp.system.entity.project;

import com.fyp.system.entity.user.Student;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "project_students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private String roleInProject;

    private boolean isTeamLeader;
}
