package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.LoginUser;
import com.example.demo.form.UserForm;
import com.example.demo.service.UserRegister;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {

	private final UserRegister userRegister;
	
	@GetMapping("/userList")
	public ModelAndView getAllUsers(ModelAndView modelAndView) {
		
		List<LoginUser> userList = userRegister.getUsers();
		
		modelAndView.setViewName("userList");
		modelAndView.addObject("userList",userList);
		return modelAndView;
		
	}
	
	@GetMapping("/save")
	public String saveNewUser(Model model) {
		model.addAttribute("userForm",new UserForm());
		return "newUserForm";
	}
	
	@PostMapping("/register")
	public ModelAndView registerUser(@ModelAttribute @Validated UserForm userForm,BindingResult result,ModelAndView modelAndView) {
		// エラーチェック
		if(result.hasErrors()) {
			modelAndView.setViewName("newUserForm");
			return modelAndView;
		}
		
		// DB登録チェック
		String errorMessage = userRegister.saveNewUser(userForm);
	
		// errorにStringが入っていたらエラー
		if(errorMessage != null) {
			modelAndView.addObject("error",errorMessage);
			modelAndView.setViewName("newUserForm");
			return modelAndView;
		}
		
		return getAllUsers(modelAndView);
	}
	
	@GetMapping("/deleteForm")
	public ModelAndView userDelete(@RequestParam("id") Integer id,ModelAndView modelAndView) {
		
		LoginUser loginUser = new LoginUser();
		loginUser = userRegister.getOneUser(id);
		
		modelAndView.setViewName("delete");
		modelAndView.addObject("loginUser",loginUser);
		
		return modelAndView;
	}
	
	@PostMapping("/delete")
	public ModelAndView userDeleteReturn(@RequestParam("id") Integer id,ModelAndView modelAndView) {
		
		userRegister.userDelete(id);
		
		return getAllUsers(modelAndView);
	}
	
	@GetMapping("/updateForm")
	public ModelAndView userUpdate(@RequestParam("id") Integer id,ModelAndView modelAndView) {
		
		LoginUser loginUser = new LoginUser();
		loginUser = userRegister.getOneUser(id);
		
		modelAndView.setViewName("update");
		modelAndView.addObject("loginUser",loginUser);
		
		return modelAndView;
	}
	
	@PostMapping("/update")
	public ModelAndView userUpdateReturn(@ModelAttribute @Validated LoginUser loginUser,BindingResult result,ModelAndView modelAndView) {
		// エラーチェック
		if(result.hasErrors()) {
			modelAndView.setViewName("update");
			return modelAndView;
		}
		
		// DB登録チェック
		String errorMessage = userRegister.saveOneUser(loginUser);
	
		// errorにStringが入っていたらエラー
		if(errorMessage != null) {
			modelAndView.addObject("error",errorMessage);
			modelAndView.setViewName("update");
			return modelAndView;
		}
		
		return getAllUsers(modelAndView);
	}
	
//	public Integer getLoginUserId() {
//		final String name = SecurityContextHolder.getContext().getAuthentication().getName();
//		
//		LoginUser loginUser = new LoginUser();
//		loginUser = userRegister.getOneUser(name);
//		
//		Integer id = loginUser.getId();
//		
//		return id;
//	}
}
