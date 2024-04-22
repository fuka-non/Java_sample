package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="users")
//@Table(name="users2")
public class LoginUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false,unique = true,name = "user_id")
	private String userId;
//	@Column(nullable = false,unique = true)
//	private String username;
	
	@Column(nullable = false)
	private String password;
	
	private String username;
//	private String user;
	private String role;
		
	
		
}
