package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.LoginUser;
import com.example.demo.form.UserForm;

public interface UserRegister {

	// 全ユーザー取得
	public List<LoginUser> getUsers();
	// 選択ユーザー取得
	public LoginUser getOneUser(Integer id);	
	public LoginUser getOneUser(String name);	
	// 選択ユーザー削除
	public void userDelete(Integer id);
	// 新規ユーザー登録
	public String saveNewUser(UserForm userForm);
	// 既存ユーザー更新
	public String saveOneUser(LoginUser loginUser);
	// 既存ユーザー確認用
	public List<String> getUserIds();
}
