package com.fyp.system.entity.grading;

import com.fyp.system.entity.document.DocumentType;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rubrics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rubric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "document_type_id")
    private DocumentType documentType;

    private String name;
    private Double totalMarks;

    @OneToMany(mappedBy = "rubric", cascade = CascadeType.ALL)
    private List<RubricCriteria> criteria = new ArrayList<>();
}
