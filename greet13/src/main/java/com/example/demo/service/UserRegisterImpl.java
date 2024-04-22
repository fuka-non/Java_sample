package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.LoginUser;
import com.example.demo.form.UserForm;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserRegisterImpl implements UserRegister {

	private final UserRepository userRepository;
	
	@Override // 全ユーザー取得
	public List<LoginUser> getUsers() {
		
		List<LoginUser> userList = new ArrayList<LoginUser>();
		userList = userRepository.findAll();
		
		return userList;
	}

	@Override // ユーザー情報１件取得
	public LoginUser getOneUser(Integer id) {
		Optional<LoginUser> loginUserOpt = userRepository.findById(id);
		LoginUser loginUser = new LoginUser();
//		loginUser = userRepository.getById(id);
		
		// 値存在チェック
		if(loginUserOpt.isPresent()) {
			
			// あれば値取得
			loginUser = loginUserOpt.get();
			
		} else {
			System.out.println("user取得エラー");
		}
		
		return loginUser;
	}
	@Override
	public LoginUser getOneUser(String name) {
		LoginUser loginUser = new LoginUser();
		loginUser = userRepository.findByUserId(name);
		
		return loginUser;
	}

	@Override	// ユーザー新規登録
	public String saveNewUser(UserForm userForm) {
		
		List<String> userIds = getUserIds();
		for(String userId:userIds) {
			if(userId.equals(userForm.getUserId())) {
				return "このユーザーIDは既に登録されています";
			}
		}
		
		LoginUser loginUser = new LoginUser();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		//IDは空
		loginUser.setUserId(userForm.getUserId());
		loginUser.setUsername(userForm.getUsername());
		loginUser.setRole(userForm.getRole());
		loginUser.setPassword(encoder.encode(userForm.getPassword()));
		
		//ユーザー登録
		try {
			userRepository.save(loginUser);
		} catch (Exception e) {
			System.out.println("DBエラー");
			return "登録できませんでした";
		}
		return null;
		
	}

	@Override	// ユーザー情報更新
	public String saveOneUser(LoginUser loginUser) {
		
		String password;
		password = loginUser.getPassword();
		
		// パスワードに更新があるとき
		if(password != null && !password.isEmpty()) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			loginUser.setPassword(encoder.encode(loginUser.getPassword()));
		}
		
		//ユーザー情報更新
			try {
				userRepository.save(loginUser);
			} catch (Exception e) {
				System.out.println("DBエラー");
				return "更新できませんでした";
			}
		return null;
	}

	@SuppressWarnings("null")
	@Override
	public List<String> getUserIds() {
		List<String> userIdList = new ArrayList<String>();
		userIdList = userRepository.getUserId();
		return userIdList;
	}
	
	@Override	// ユーザー削除
	public void userDelete(Integer id) {
		
		userRepository.deleteById(id);
	}

}
