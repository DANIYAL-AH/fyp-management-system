package com.fyp.system.entity.project;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "project_resources")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    private String resourceName;
    private String filePath;
    private String resourceType;
}
