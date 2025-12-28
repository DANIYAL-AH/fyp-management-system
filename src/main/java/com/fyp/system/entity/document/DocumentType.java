package com.fyp.system.entity.document;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "document_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String typeName;

    private boolean isRequired;
    private Long maxFileSize;
    private String allowedExtensions;
}
