package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.LoginUser;

public interface UserRepository extends JpaRepository<LoginUser, Integer> {
	
	public LoginUser findByUserId(String userId);
	@Query("select u.userId from LoginUser u")
	public List<String> getUserId();
	
}
