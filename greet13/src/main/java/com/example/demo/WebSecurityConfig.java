package com.example.demo;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.*;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.formLogin(login -> login
									  .loginPage("/login")
									  .loginProcessingUrl("/login")
									  .usernameParameter("user_id")
//									  .usernameParameter("username")
									  .defaultSuccessUrl("/", true)
									  .failureUrl("/login?error=true")
									  .permitAll()
									 ).logout(logout -> logout
									  .logoutSuccessUrl("/")
									 ).authorizeHttpRequests(authz -> authz
											  .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
											  .permitAll()
										      .requestMatchers(antMatcher("/")).permitAll()
										      .requestMatchers(antMatcher("/input")).hasRole("ADMIN")
										      .requestMatchers(antMatcher("/userList")).hasRole("ADMIN")
										      .anyRequest().authenticated()
										      );
		return http.build();
				
	}
	
}
