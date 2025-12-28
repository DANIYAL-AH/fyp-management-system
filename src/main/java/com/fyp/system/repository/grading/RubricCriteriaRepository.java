package com.fyp.system.repository.grading;

import com.fyp.system.entity.grading.RubricCriteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RubricCriteriaRepository extends JpaRepository<RubricCriteria, Long> {
}
