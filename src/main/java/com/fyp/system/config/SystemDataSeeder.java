package com.fyp.system.config;

import com.fyp.system.entity.user.*;
import com.fyp.system.repository.user.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration
public class SystemDataSeeder {

    @Bean
    CommandLineRunner initDatabase(RoleRepository roleRepository, 
                                   UserRepository userRepository,
                                   StudentRepository studentRepository,
                                   SupervisorRepository supervisorRepository,
                                   PasswordEncoder passwordEncoder) {
        return args -> {
            // 1. Create Roles
            if (roleRepository.count() == 0) {
                Arrays.stream(RoleName.values()).forEach(roleName -> {
                    roleRepository.save(new Role(roleName));
                });
            }

            // 2. Create System Admin
            seedUser(userRepository, roleRepository, passwordEncoder, "admin@uet.edu.pk", "System Admin", "Admin@123", RoleName.SYSTEM_ADMIN);

            // 3. Create Supervisor with Profile
            seedSupervisor(userRepository, roleRepository, supervisorRepository, passwordEncoder);

            // 4. Create Student with Profile
            seedStudent(userRepository, roleRepository, studentRepository, passwordEncoder);
        };
    }

    private void seedUser(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, String email, String name, String password, RoleName roleName) {
        if (!userRepository.existsByEmail(email)) {
            User user = new User();
            user.setEmail(email);
            user.setFullName(name);
            user.setPasswordHash(passwordEncoder.encode(password));
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());

            Role role = roleRepository.findByName(roleName)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

            UserRole userRole = new UserRole(user, role);
            user.getRoles().add(userRole);

            userRepository.save(user);
            System.out.println("System Admin seeded successfully.");
        }
    }

    private void seedSupervisor(UserRepository userRepository, RoleRepository roleRepository, SupervisorRepository supervisorRepository, PasswordEncoder passwordEncoder) {
        String email = "supervisor@uet.edu.pk";
        if (!userRepository.existsByEmail(email)) {
            // 1. Create User
            User user = new User();
            user.setEmail(email);
            user.setFullName("Dr. Supervisor");
            user.setPasswordHash(passwordEncoder.encode("Supervisor@123"));
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());

            Role role = roleRepository.findByName(RoleName.SUPERVISOR)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            
            UserRole userRole = new UserRole(user, role);
            user.getRoles().add(userRole);

            // Save User first
            User savedUser = userRepository.save(user);

            // 2. Create Supervisor Profile
            Supervisor supervisor = new Supervisor();
            supervisor.setUser(savedUser);
            supervisor.setEmployeeId("EMP-001");
            supervisor.setSpecialization("Artificial Intelligence");
            
            supervisorRepository.save(supervisor);
            System.out.println("✅ Created Profile for Supervisor: " + supervisor.getEmployeeId());
        }
    }

    private void seedStudent(UserRepository userRepository, RoleRepository roleRepository, StudentRepository studentRepository, PasswordEncoder passwordEncoder) {
        String email = "student@uet.edu.pk";
        if (!userRepository.existsByEmail(email)) {
            // 1. Create User
            User user = new User();
            user.setEmail(email);
            user.setFullName("Student One");
            user.setPasswordHash(passwordEncoder.encode("Student@123"));
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());

            Role role = roleRepository.findByName(RoleName.STUDENT)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            
            UserRole userRole = new UserRole(user, role);
            user.getRoles().add(userRole);

            // Save User first
            User savedUser = userRepository.save(user);

            // 2. Create Student Profile
            Student student = new Student();
            student.setUser(savedUser);
            student.setRegistrationNumber("2021-CS-101");
            student.setDepartment("Computer Science");
            student.setCgpa(3.5);
            
            studentRepository.save(student);
            System.out.println("✅ Created Profile for Student: " + student.getRegistrationNumber());
        }
    }
}
