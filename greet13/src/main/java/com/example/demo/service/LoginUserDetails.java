package com.example.demo.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.LoginUser;

public class LoginUserDetails implements UserDetails {

	private final LoginUser loginUser;
	
	public LoginUserDetails(LoginUser loginUser) {
		this.loginUser = loginUser;
	}
	
	@Override //ユーザ権限
	public Collection<? extends GrantedAuthority> getAuthorities(){
		
		return AuthorityUtils.createAuthorityList("ROLE_" + loginUser.getRole());
	}
	
	public String getUserId() {
		
		return loginUser.getUserId();
	}
	
//	public String getUser() {
//		return loginUser.getUser();
//	}
	
	@Override //パスワード
	public String getPassword() {
		
		return loginUser.getPassword();
	}
	
	@Override
	public String getUsername() {
		
		return loginUser.getUsername();
	}
	
	@Override // アカウントの期限
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override //アカウントロック
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override //パスワードの期k元
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override //有効ユーザ
	public boolean isEnabled() {
		return true;
	}
}
