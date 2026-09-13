package com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.controlller;

import java.util.List;

import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.entity.Student;
import com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.service.StudentService;

import ch.qos.logback.core.model.Model;



@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Add Student
    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    // Get All Students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Get Student By ID
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    // Update Student
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id,
                                 @RequestBody Student student) {

        return studentService.updateStudent(id, student);
    }

    // Delete Student
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }
    
 // Search By Name
    @GetMapping("/search/name")
    public List<Student> searchByName(@RequestParam String name){
        return studentService.searchByName(name);
    }

    // Search By Email
    @GetMapping("/search/email")
    public List<Student> searchByEmail(@RequestParam String email){
        return studentService.searchByEmail(email);
    }

    // Search By Department
    @GetMapping("/search/department")
    public List<Student> searchByDepartment(@RequestParam String department){
        return studentService.searchByDepartment(department);
    }

    // Search By City
    @GetMapping("/search/city")
    public List<Student> searchByCity(@RequestParam String city){
        return studentService.searchByCity(city);
    }

    // Pagination + Sorting
    @GetMapping("/page")
    public Page getStudents(

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "5") int size,

            @RequestParam(defaultValue = "id") String sortBy,

            @RequestParam(defaultValue = "asc") String direction){

        return (Page) studentService.getStudents(page,size,sortBy,direction);
    }
   

}
