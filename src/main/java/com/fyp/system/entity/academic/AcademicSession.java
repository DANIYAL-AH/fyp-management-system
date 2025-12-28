package com.fyp.system.entity.academic;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "academic_sessions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AcademicSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String sessionName;

    private LocalDate startDate;
    private LocalDate endDate;

    private boolean isActive;
}
