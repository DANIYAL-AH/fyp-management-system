package com.fyp.system.repository.academic;

import com.fyp.system.entity.academic.AcademicSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicSessionRepository extends JpaRepository<AcademicSession, Long> {
}
