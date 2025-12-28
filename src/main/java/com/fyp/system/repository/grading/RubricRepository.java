package com.fyp.system.repository.grading;

import com.fyp.system.entity.grading.Rubric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RubricRepository extends JpaRepository<Rubric, Long> {
}
