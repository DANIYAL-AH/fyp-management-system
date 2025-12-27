package com.fyp.system.controller;

import com.fyp.system.enums.DocumentType;
import com.fyp.system.enums.Role;
import com.fyp.system.model.Deadline;
import com.fyp.system.model.User;
import com.fyp.system.repository.DeadlineRepository;
import com.fyp.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DeadlineRepository deadlineRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("deadlines", deadlineRepository.findAll());
        return "admin/dashboard";
    }

    @PostMapping("/users/add")
    public String addUser(@RequestParam String username, @RequestParam String password, @RequestParam Role role) {
        User user = new User(username, passwordEncoder.encode(password), role);
        // Note: For full implementation, we should create specific subclass instances (Student, Supervisor, etc.)
        // For this basic Admin Controller, we are just creating the base User or would need logic to create specific types.
        // Given the complexity, this might be simplified for now or we just save User (which works with JOINED but won't have subclass fields).
        // Ideally: Factory pattern or switch case.
        userRepository.save(user);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/deadlines/set")
    public String setDeadline(@RequestParam DocumentType type, @RequestParam String date) {
        // date format: yyyy-MM-ddTHH:mm
        LocalDateTime deadlineDate = LocalDateTime.parse(date);
        
        Deadline deadline = deadlineRepository.findByDocumentType(type)
                .orElse(new Deadline());
        
        deadline.setDocumentType(type);
        deadline.setDeadlineDate(deadlineDate);
        deadlineRepository.save(deadline);
        
        return "redirect:/admin/dashboard";
    }
}
