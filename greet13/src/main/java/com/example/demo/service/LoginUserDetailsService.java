package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.LoginUser;
import com.example.demo.repository.LoginUserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LoginUserDetailsService implements UserDetailsService {

	private final LoginUserRepository loginUserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		
		LoginUser loginUser = loginUserRepository.findByUserId(userId);
//		LoginUser loginUser = loginUserRepository.findByUsername(userId);
		if(loginUser == null) {
			throw new UsernameNotFoundException("ユーザーが見つかりません");
		}
		
		return new LoginUserDetails(loginUser) ;
	}

}
