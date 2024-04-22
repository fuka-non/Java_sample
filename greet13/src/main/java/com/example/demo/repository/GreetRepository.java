package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.GreetMessage;

public interface GreetRepository extends JpaRepository<GreetMessage, Integer>{
	
	public GreetMessage findBylanguage(String language);
	public List<GreetMessage> findAll();
	@Query("select gm.language from GreetMessage gm")
	public List<String> getLanguages();
}
