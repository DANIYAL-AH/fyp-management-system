package com.fyp.system.repository;

import com.fyp.system.model.Deadline;
import com.fyp.system.enums.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeadlineRepository extends JpaRepository<Deadline, Long> {
    Optional<Deadline> findByDocumentType(DocumentType documentType);
}
