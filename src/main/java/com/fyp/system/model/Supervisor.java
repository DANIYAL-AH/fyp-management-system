package com.fyp.system.model;

import com.fyp.system.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Table(name = "supervisors")
public class Supervisor extends User {
    
    private String specialization;

    public Supervisor(String username, String password, String specialization) {
        super(username, password, Role.SUPERVISOR);
        this.specialization = specialization;
    }
}
