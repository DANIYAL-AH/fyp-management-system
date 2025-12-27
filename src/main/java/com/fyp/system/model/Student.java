package com.fyp.system.model;

import com.fyp.system.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Table(name = "students")
public class Student extends User {
    
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public Student(String username, String password) {
        super(username, password, Role.STUDENT);
    }
}
