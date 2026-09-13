package com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.repository;


import java.util.List;
import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    
    // Search User by Name
    List<User> findByNameContainingIgnoreCase(String name);


    // Filter User by Status
    List<User> findByStatus(String status);


    // Pagination
    Page<User> findAll(Pageable pageable);
}
