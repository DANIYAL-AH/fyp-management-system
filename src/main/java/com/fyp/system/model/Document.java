package com.fyp.system.model;

import com.fyp.system.enums.DocumentStatus;
import com.fyp.system.enums.DocumentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fileName;

    private LocalDateTime uploadTimestamp;

    private boolean isLocked = false;

    @Enumerated(EnumType.STRING)
    private DocumentStatus status;

    @Enumerated(EnumType.STRING)
    private DocumentType type;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @PrePersist
    protected void onCreate() {
        if (uploadTimestamp == null) {
            uploadTimestamp = LocalDateTime.now();
        }
    }
}
