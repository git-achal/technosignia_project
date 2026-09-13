	package com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.dto.LoginRequest;
import com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.dto.SignupRequest;
import com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.entity.User;

public interface UserService {

    String signup(SignupRequest request);

    String login(LoginRequest request);

    String logout();

    // ================= USER MANAGEMENT =================

    // Get all users
    List<User> getAllUsers();


    // Get user by ID
    User getUserById(Long id);


    // Update user
    User updateUser(Long id, User user);


    // Delete user
    String deleteUser(Long id);


    // Search user by name
    List<User> searchByName(String name);


    // Filter user by status
    List<User> filterByStatus(String status);


    // Pagination and Sorting
    Page<User> getUsers(int page,
                        int size,
                        String sortBy,
                        String direction);
    
    User getLoggedInUser();
    
  //  User updateUser(Long id, User user);

    String changePassword(Long userId,
                         String currentPassword,
                         String newPassword,
                         String confirmPassword);

    //String deleteUser(Long id);
}


