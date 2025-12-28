package com.fyp.system.repository.project;

import com.fyp.system.entity.project.ProjectStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectStudentRepository extends JpaRepository<ProjectStudent, Long> {
}
