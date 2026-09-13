package com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.dto.LoginRequest;
import com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.dto.SignupRequest;
import com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.entity.Role;
import com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.entity.User;
import com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.repository.UserRepository;
import com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.service.UserService;
import com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.audit.AuditService;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HttpSession session;

    @Autowired
    private AuditService auditService;


    // Signup
    @Override
    public String signup(SignupRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email already exists!";
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        // Encrypt password
        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        user.setStatus("ACTIVE");
        user.setRole(Role.USER);
        user.setCreatedDate(LocalDateTime.now());

        userRepository.save(user);
        auditService.log("REGISTRATION", "USER", user.getId(), "User registered");

        return "User Registered Successfully";
    }


    // Login
    @Override
    public String login(LoginRequest request) {

        Optional<User> optionalUser =
                userRepository.findByEmail(request.getEmail());

        if (optionalUser.isEmpty()) {
            return "Invalid Email";
        }

        User user = optionalUser.get();

        // Check password
        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            return "Invalid Password";
        }

        // Check status
        if (!"ACTIVE".equalsIgnoreCase(user.getStatus())) {
            return "User is not active";
        }

        // Save user
        session.setAttribute("loggedUser", user);
        auditService.log("LOGIN", "USER", user.getId(), "User logged in");

        return "Login Successful";
    }


    // Logout
    @Override
    public String logout() {

        User u = (User) session.getAttribute("loggedUser");
        if (u != null) auditService.log("LOGOUT", "USER", u.getId(), "User logged out");
        session.invalidate();

        return "Logout Successful";
    }


    // Get all users
    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }


    // Get user by ID
    @Override
    public User getUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User Not Found"));
    }


    // Update user
    @Override
    public User updateUser(Long id, User user) {

        User existingUser =
                userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User Not Found"));

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());

        return userRepository.save(existingUser);
    }


    // Delete user
    @Override
    public String deleteUser(Long id) {

        User user =
                userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User Not Found"));

        userRepository.delete(user);

        return "User Deleted Successfully";
    }


    // Search user
    @Override
    public List<User> searchByName(String name) {

        return userRepository
                .findByNameContainingIgnoreCase(name);
    }


    // Filter user
    @Override
    public List<User> filterByStatus(String status) {

        return userRepository.findByStatus(status);
    }


    // Pagination
    @Override
    public Page<User> getUsers(int page,
                               int size,
                               String sortBy,
                               String direction) {

        Sort sort;

        if (direction.equalsIgnoreCase("desc")) {

            sort = Sort.by(sortBy).descending();

        } else {

            sort = Sort.by(sortBy).ascending();
        }

        Pageable pageable =
                PageRequest.of(page, size, sort);

        return userRepository.findAll(pageable);
    }


    // Logged user
    @Override
    public User getLoggedInUser() {

        return (User) session.getAttribute("loggedUser");
    }


    // Change password
    @Override
    public String changePassword(Long userId,
                                 String currentPassword,
                                 String newPassword,
                                 String confirmPassword) {

        User user =
                userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User Not Found"));

        // Check current
        if (!passwordEncoder.matches(
                currentPassword,
                user.getPassword())) {

            return "Current password is incorrect";
        }

        // Check new
        if (!newPassword.equals(confirmPassword)) {

            return "New passwords do not match";
        }

        // Encrypt new password
        user.setPassword(
                passwordEncoder.encode(newPassword)
        );

        userRepository.save(user);
        auditService.log("PASSWORD_CHANGE", "USER", user.getId(), "Password changed");

        return "Password changed successfully";
    }
}