package com.example.demo.service;

import java.util.List;

import com.example.demo.form.GreetForm;

public interface GreetService {
	
	public String saveGreetMessage(GreetForm greetForm);
	public List<String> getLanguages();
	public String sayMessage(String language,String timeframe);
}
