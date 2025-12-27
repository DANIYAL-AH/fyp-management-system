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
@Table(name = "evaluators")
public class Evaluator extends User {
    public Evaluator(String username, String password) {
        super(username, password, Role.EVALUATOR);
    }
}
