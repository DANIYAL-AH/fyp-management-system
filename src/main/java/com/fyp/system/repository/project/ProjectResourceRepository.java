package com.fyp.system.repository.project;

import com.fyp.system.entity.project.ProjectResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectResourceRepository extends JpaRepository<ProjectResource, Long> {
}
