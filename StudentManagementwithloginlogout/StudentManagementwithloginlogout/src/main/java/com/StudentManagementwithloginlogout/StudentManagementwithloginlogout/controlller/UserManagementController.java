package com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.controlller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.entity.User;
import com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserManagementController {

    @Autowired
    private UserService userService;


    // =====================================================
    // 1. GET ALL USERS
    // =====================================================

    @GetMapping
    public List<User> getAllUsers() {

        return userService.getAllUsers();
    }


    // =====================================================
    // 2. GET USER BY ID
    // =====================================================

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {

        return userService.getUserById(id);
    }


    // =====================================================
    // 3. UPDATE USER
    // =====================================================

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id,
                           @RequestBody User user) {

        return userService.updateUser(id, user);
    }


    // =====================================================
    // 4. DELETE USER
    // =====================================================

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {

        return userService.deleteUser(id);
    }


    // =====================================================
    // 5. SEARCH USER BY NAME
    // =====================================================

    @GetMapping("/search")
    public List<User> searchByName(
            @RequestParam String name) {

        return userService.searchByName(name);
    }


    // =====================================================
    // 6. FILTER USER BY STATUS
    // =====================================================

    @GetMapping("/status")
    public List<User> filterByStatus(
            @RequestParam String status) {

        return userService.filterByStatus(status);
    }


    // =====================================================
    // 7. PAGINATION AND SORTING
    // =====================================================

    @GetMapping("/page")
    public Page<User> getUsers(

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "5")
            int size,

            @RequestParam(defaultValue = "id")
            String sortBy,

            @RequestParam(defaultValue = "asc")
            String direction) {


        return userService.getUsers(
                page,
                size,
                sortBy,
                direction);
    }
}
