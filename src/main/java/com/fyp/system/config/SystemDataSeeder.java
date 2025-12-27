package com.fyp.system.config;

import com.fyp.system.enums.DocumentType;
import com.fyp.system.enums.Role;
import com.fyp.system.model.*;
import com.fyp.system.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class SystemDataSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SupervisorRepository supervisorRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private DeadlineRepository deadlineRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() > 0) return;

        // 1. Admin
        Admin admin = new Admin("admin", passwordEncoder.encode("admin123"));
        userRepository.save(admin);

        // 2. Committee
        Committee committee = new Committee("committee", passwordEncoder.encode("committee123"));
        userRepository.save(committee);

        // 3. Supervisors
        Supervisor sup1 = new Supervisor("supervisor1", passwordEncoder.encode("password123"), "AI & ML");
        Supervisor sup2 = new Supervisor("supervisor2", passwordEncoder.encode("password123"), "IoT & Embedded");
        supervisorRepository.saveAll(Arrays.asList(sup1, sup2));

        // 4. Project Group & Students
        Project project = new Project();
        project.setTitle("Smart Traffic Management System");
        project.setDescription("AI-based traffic control using computer vision.");
        project.setSupervisor(sup1);
        project = projectRepository.save(project);

        Student s1 = new Student("student1", passwordEncoder.encode("password123"));
        s1.setProject(project);
        Student s2 = new Student("student2", passwordEncoder.encode("password123"));
        s2.setProject(project);
        Student s3 = new Student("student3", passwordEncoder.encode("password123"));
        s3.setProject(project);
        Student s4 = new Student("student4", passwordEncoder.encode("password123"));
        s4.setProject(project);

        studentRepository.saveAll(Arrays.asList(s1, s2, s3, s4));

        // 5. Deadlines
        createDeadline(DocumentType.PROPOSAL, LocalDateTime.now().plusDays(7));
        createDeadline(DocumentType.DESIGN, LocalDateTime.now().plusDays(30));
        createDeadline(DocumentType.TESTING, LocalDateTime.now().plusDays(60));
        createDeadline(DocumentType.THESIS, LocalDateTime.now().plusDays(90));
        
        System.out.println("System Data Seeded Successfully!");
    }

    private void createDeadline(DocumentType type, LocalDateTime date) {
        Deadline deadline = new Deadline();
        deadline.setDocumentType(type);
        deadline.setDeadlineDate(date);
        deadlineRepository.save(deadline);
    }
}
