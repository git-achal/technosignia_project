	package com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.entity;
	
	import java.time.LocalDateTime;
	
	import jakarta.persistence.*;
	
	@Entity
	@Table(name = "users")
	public class User {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	
	    private String name;
	
	    @Column(unique = true, nullable = false)
	    private String email;
	
	    private String password;
	
	    private String status;
	    
	    @Enumerated(EnumType.STRING)
	    private Role role;
	    
	    private LocalDateTime createdDate;
	    
	    public User(Long id, String name, String email, String password, String status, Role role,
				LocalDateTime createdDate) {
			super();
			this.id = id;
			this.name = name;
			this.email = email;
			this.password = password;
			this.status = status;
			this.role = role;
			this.createdDate = createdDate;
		}

	
	    
	   
	
	
	    // Default Constructor
	    public User() {
	    }
	
	
	    // Parameterized Constructor
	    public User(Long id,
	                String name,
	                String email,
	                String password,
	                String status,
	                LocalDateTime createdDate) {
	
	        this.id = id;
	        this.name = name;
	        this.email = email;
	        this.password = password;
	        this.status = status;
	        this.createdDate = createdDate;
	    }
	
	
	    // ID
	    public Long getId() {
	        return id;
	    }
	
	    public void setId(Long id) {
	        this.id = id;
	    }
	
	
	    // Name
	    public String getName() {
	        return name;
	    }
	
	    public void setName(String name) {
	        this.name = name;
	    }
	
	
	    // Email
	    public String getEmail() {
	        return email;
	    }
	
	    public void setEmail(String email) {
	        this.email = email;
	    }
	
	
	    // Password
	    public String getPassword() {
	        return password;
	    }
	
	    public void setPassword(String password) {
	        this.password = password;
	    }
	
	
	    // Status
	    public String getStatus() {
	        return status;
	    }
	
	    public void setStatus(String status) {
	        this.status = status;
	    }
	 
	    public Role getRole() {
	        return role;
	    }

	    public void setRole(Role role) {
	        this.role = role;
	    }
	
	    // Created Date
	    public LocalDateTime getCreatedDate() {
	        return createdDate;
	    }
	
	    public void setCreatedDate(LocalDateTime createdDate) {
	        this.createdDate = createdDate;
	    }
	}