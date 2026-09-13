package com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.controlller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.entity.Student;
import com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.entity.User;
import com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.repository.UserRepository;
import com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.service.StudentService;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.audit.AuditService;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;



@Controller
public class PageController {

    @Autowired
    private UserRepository userRepository;
    
    
    @Autowired
    private StudentService studentService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuditService auditService;

    // Register Page
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    // Login Page
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // Home Page
    @GetMapping("/home")
    public String homePage() {
        return "home";
    }

 // Save User
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user,
                               Model model) {

        if (userRepository.existsByEmail(user.getEmail())) {

            model.addAttribute("error",
                    "Email already registered");

            return "register";
        }

        user.setStatus("ACTIVE");
        user.setRole(com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.entity.Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setCreatedDate(LocalDateTime.now());

        userRepository.save(user);
        auditService.logAs("REGISTRATION", user.getEmail(), "USER", user.getId(), "User registered");
        model.addAttribute("success",
                "Registration Successful! Please Login.");

        return "login";
    }
   //login user
    @PostMapping("/login")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password,
                            Model model,
                            HttpSession session) {

        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent()) {

            User loggedUser = user.get();

            if (passwordEncoder.matches(password, loggedUser.getPassword())) {

                // Store logged-in user in session
                session.setAttribute("loggedUser", loggedUser);
                auditService.log("LOGIN", "USER", loggedUser.getId(), "User logged in");

                // Keep name available for the current page
                model.addAttribute("name", loggedUser.getName());

                return "home";
            }
        }

        model.addAttribute("error", "Invalid Email or Password");

        return "login";
    }
    @GetMapping("/dashboard")
    public String dashboard(Model model,
                            HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loggedUser") == null) {
            return "redirect:/login";
        }

        model.addAttribute("totalStudents",
                studentService.getAllStudents().size());

        model.addAttribute("activeStudents",
                studentService.getAllStudents().size());

        model.addAttribute("students",
                studentService.getAllStudents());

        return "dashboard";
    }
   

    @GetMapping("/students")
    public String studentsPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            Model model,
            HttpServletRequest request) {

        // Check login session
        HttpSession session = request.getSession(false);

        if (session == null ||
            session.getAttribute("loggedUser") == null) {

            return "redirect:/login";
        }

        // Pagination + sorting
        Page<Student> studentPage =
                studentService.getStudents(
                        page,
                        size,
                        sortBy,
                        direction
                );

        model.addAttribute("students",
                studentPage.getContent());

        model.addAttribute("currentPage",
                studentPage.getNumber());

        model.addAttribute("totalPages",
                studentPage.getTotalPages());

        model.addAttribute("totalItems",
                studentPage.getTotalElements());

        model.addAttribute("size", size);

        model.addAttribute("sortBy", sortBy);

        model.addAttribute("direction", direction);

        return "students";
    }
    
   
    
    @GetMapping("/students/search")
    public String searchStudents(
            @RequestParam String keyword,
            Model model) {

        List<Student> students;

        if (keyword == null || keyword.trim().isEmpty()) {

            students = studentService.getAllStudents();

        } else {

            students = studentService.searchByName(keyword);

            if (students.isEmpty()) {
                students = studentService.searchByEmail(keyword);
            }

            if (students.isEmpty()) {
                students = studentService.searchByDepartment(keyword);
            }

            if (students.isEmpty()) {
                students = studentService.searchByCity(keyword);
            }
        }

        model.addAttribute("students", students);

        return "students";
    }
    
    @GetMapping("/addStudent")
    public String addStudentPage(Model model,
                                 HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loggedUser") == null) {
            return "redirect:/login";
        }

        model.addAttribute("student", new Student());

        return "add-student";
    }
    
    
    @PostMapping("/addStudent")
    public String addStudent(@ModelAttribute("student") Student student) {

        studentService.addStudent(student);

        return "redirect:/students";
    }
 // Show Edit Student Page
    @GetMapping("/editStudent/{id}")
    public String editStudentPage(@PathVariable Long id,
                                  Model model,
                                  HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loggedUser") == null) {
            return "redirect:/login";
        }

        Student student = studentService.getStudentById(id);

        model.addAttribute("student", student);

        return "edit-student";
    }


    // Update Student
    @PostMapping("/editStudent/{id}")
    public String updateStudent(@PathVariable Long id,
                                @ModelAttribute Student student) {

        studentService.updateStudent(id, student);

        return "redirect:/students";
    }
 // Delete Student
    @PostMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable Long id) {

        studentService.deleteStudent(id);

        return "redirect:/students";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {

        // Remove logged-in user from session
        auditService.log("LOGOUT", "USER", ((User) session.getAttribute("loggedUser")).getId(), "User logged out");
        session.invalidate();

        // Redirect to login page
        return "redirect:/login";
    }
   
}