package com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.entity.Student;



public interface StudentService {

    Student addStudent(Student student);

    List<Student> getAllStudents();

    Student getStudentById(Long id);

    Student updateStudent(Long id, Student student);

    String deleteStudent(Long id);

    // Search APIs
    List<Student> searchByName(String name);

    List<Student> searchByEmail(String email);

    List<Student> searchByDepartment(String department);

    List<Student> searchByCity(String city);

    // Pagination & Sorting
    Page<Student> getStudents(int page,
                              int size,
                              String sortBy,
                              String direction);

}