package com.fyp.system.repository.academic;

import com.fyp.system.entity.academic.CommitteeMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommitteeMemberRepository extends JpaRepository<CommitteeMember, Long> {
}
