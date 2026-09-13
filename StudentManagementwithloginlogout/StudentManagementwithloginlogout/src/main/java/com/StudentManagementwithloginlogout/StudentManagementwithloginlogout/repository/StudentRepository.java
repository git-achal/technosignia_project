package com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.entity.Student;



public interface StudentRepository extends JpaRepository<Student, Long> {

    // Search By Name
    List<Student> findByNameContainingIgnoreCase(String name);

    // Search By Email
    List<Student> findByEmailContainingIgnoreCase(String email);

    // Search By Department
    List<Student> findByDepartmentContainingIgnoreCase(String department);

    // Search By City
    List<Student> findByCityContainingIgnoreCase(String city);

    // Pagination
    Page<Student> findAll(Pageable pageable);

}
