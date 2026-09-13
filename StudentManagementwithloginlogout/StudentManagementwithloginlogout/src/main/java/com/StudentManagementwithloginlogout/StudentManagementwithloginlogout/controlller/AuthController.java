package com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.controlller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.dto.LoginRequest;
import com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.dto.SignupRequest;
import com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.service.UserService;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    // Signup API
    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest request) {
        return userService.signup(request);
    }

    // Login API
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }

    // Logout API
    @PostMapping("/logout")
    public String logout() {
        return userService.logout();
    }
}