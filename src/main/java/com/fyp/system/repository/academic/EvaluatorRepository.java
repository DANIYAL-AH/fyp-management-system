package com.fyp.system.repository.academic;

import com.fyp.system.entity.academic.Evaluator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluatorRepository extends JpaRepository<Evaluator, Long> {
}
