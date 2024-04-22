package com.example.demo.form;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserForm {

	@NotNull
	@Size(min = 5,message = "5文字以上で入力")
	private String userId;
	
	@NotNull
	@Size(min = 10,message = "10文字以上で入力")
	@Pattern(regexp = "[a-zA-Z0-9]*",message = "英大小文字、数値を組み合わせて入力")
	private String password;
	
	@NotNull
	private String username;
	
	private String role;
}
