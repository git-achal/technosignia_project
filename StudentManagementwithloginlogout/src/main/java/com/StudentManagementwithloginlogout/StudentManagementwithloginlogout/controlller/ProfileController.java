package com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.controlller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.entity.User;
import com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;


    // ================= VIEW PROFILE =================

    @GetMapping("/profile")
    public String profilePage(
            Model model,
            HttpSession session) {

        User loggedUser =
                (User) session.getAttribute("loggedUser");

        // User is not logged in
        if (loggedUser == null) {
            return "redirect:/login";
        }

        // Send logged-in user to profile.html
        model.addAttribute("user", loggedUser);

        return "profile";
    }


    // ================= EDIT PROFILE PAGE =================

    @GetMapping("/profile/edit")
    public String editProfilePage(
            Model model,
            HttpSession session) {

        User loggedUser =
                (User) session.getAttribute("loggedUser");

        // User is not logged in
        if (loggedUser == null) {
            return "redirect:/login";
        }

        model.addAttribute("user", loggedUser);

        return "edit-profile";
    }


    // ================= UPDATE PROFILE =================

    @PostMapping("/profile/update")
    public String updateProfile(
            @ModelAttribute User updatedUser,
            Model model,
            HttpSession session) {

        User loggedUser =
                (User) session.getAttribute("loggedUser");

        // User is not logged in
        if (loggedUser == null) {
            return "redirect:/login";
        }


        // Update Name
        loggedUser.setName(updatedUser.getName());


        // Update Email
        loggedUser.setEmail(updatedUser.getEmail());


        // Save using UserService
        User updated =
                userService.updateUser(
                        loggedUser.getId(),
                        loggedUser
                );


        // Update session with latest user
        session.setAttribute("loggedUser", updated);


        return "redirect:/profile";
    }
    @PostMapping("/profile/change-password")
    public String changePassword(
            @RequestParam String currentPassword,
            @RequestParam String newPassword,
            @RequestParam String confirmPassword,
            Model model,
            HttpSession session) {

        User loggedUser =
                (User) session.getAttribute("loggedUser");

        if (loggedUser == null) {
            return "redirect:/login";
        }

        String result = userService.changePassword(
                loggedUser.getId(),
                currentPassword,
                newPassword,
                confirmPassword
        );

        if (!result.equals("Password changed successfully")) {

            model.addAttribute("error", result);

            model.addAttribute("user", loggedUser);

            return "profile";
        }

        model.addAttribute("success", result);

        model.addAttribute("user", loggedUser);

        return "profile";
    }
   

}