package com.fyp.system.entity.academic;

import com.fyp.system.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "committee_members")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommitteeMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String position;
    private String department;
}
