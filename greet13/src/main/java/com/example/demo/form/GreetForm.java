package com.example.demo.form;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class GreetForm {

	@NotNull
	@Size(min=1,max=20,message="言語は１文字以上２０文字以下で指定")
	private String language;

	@NotNull
	@Size(min=1,max=64,message = "挨拶は１文字以上６４文字以下で指定")
	private String morning;
	
	@NotNull
	@Size(min=1,max=64,message = "挨拶は１文字以上６４文字以下で指定")
	private String noon;

	@NotNull
	@Size(min=1,max=64,message = "挨拶は１文字以上６４文字以下で指定")
	private String evening;
	
	@NotNull
	@Size(min=1,max=64,message = "挨拶は１文字以上６４文字以下で指定")
	private String night;
	
//	@NotNull
//	@Size(min=1,max=64,message = "挨拶は１文字以上６４文字以下で指定")
//	private String bye;
	
	@Size(max=256,message = "備考は２５６文字以下で指定")
	private String remarks;

	
}
