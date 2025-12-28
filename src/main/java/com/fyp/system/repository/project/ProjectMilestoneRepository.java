package com.fyp.system.repository.project;

import com.fyp.system.entity.project.ProjectMilestone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectMilestoneRepository extends JpaRepository<ProjectMilestone, Long> {
}
