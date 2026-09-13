package com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.serviceImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.entity.Student;
import com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.repository.StudentRepository;
import com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.service.StudentService;
import com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.audit.AuditService;



@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AuditService auditService;

    @Override
    public Student addStudent(Student student) {
        Student saved = studentRepository.save(student);
        auditService.log("CREATE", "STUDENT", saved.getId(), "Student created");
        return saved;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {

        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student Not Found"));
    }

    @Override
    public Student updateStudent(Long id, Student student) {

        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student Not Found"));

        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setDepartment(student.getDepartment());
        existingStudent.setCity(student.getCity());
        existingStudent.setPhone(student.getPhone());

        Student saved = studentRepository.save(existingStudent);
        auditService.log("UPDATE", "STUDENT", id, "Student updated");
        return saved;
    }

    @Override
    public String deleteStudent(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student Not Found"));

        studentRepository.delete(student);
        auditService.log("DELETE", "STUDENT", id, "Student deleted");

        return "Student Deleted Successfully";
    }

    @Override
    public List<Student> searchByName(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Student> searchByEmail(String email) {
        return studentRepository.findByEmailContainingIgnoreCase(email);
    }

    @Override
    public List<Student> searchByDepartment(String department) {
        return studentRepository.findByDepartmentContainingIgnoreCase(department);
    }

    @Override
    public List<Student> searchByCity(String city) {
        return studentRepository.findByCityContainingIgnoreCase(city);
    }

    @Override
    public Page<Student> getStudents(int page,
                                     int size,
                                     String sortBy,
                                     String direction) {

        Sort sort;

        if(direction.equalsIgnoreCase("desc")) {
            sort = Sort.by(sortBy).descending();
        } else {
            sort = Sort.by(sortBy).ascending();
        }

        Pageable pageable = PageRequest.of(page, size, sort);

        return studentRepository.findAll(pageable);

    }

}
