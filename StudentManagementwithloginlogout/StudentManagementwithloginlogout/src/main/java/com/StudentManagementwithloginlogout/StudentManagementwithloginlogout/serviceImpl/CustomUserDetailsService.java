package com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.entity.User;
import com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                    new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles((user.getRole()==null?com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.entity.Role.USER:user.getRole()).name())
                .build();
    }
}
