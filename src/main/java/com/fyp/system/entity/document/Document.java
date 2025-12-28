package com.fyp.system.entity.document;

import com.fyp.system.entity.project.Project;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "documents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private DocumentType documentType;

    private String title;
    private String description;
    private String filePath;
    private String fileName;
    private Long fileSize;
    private Integer versionNumber;
    private boolean isSubmitted;
    private boolean isLocked;
    private String currentStatus;

    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL)
    private List<DocumentVersion> versions = new ArrayList<>();
}
