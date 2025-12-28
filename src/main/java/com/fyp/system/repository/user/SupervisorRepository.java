package com.fyp.system.repository.user;

import com.fyp.system.entity.user.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor, Long> {
    Optional<Supervisor> findByEmployeeId(String employeeId);
}
