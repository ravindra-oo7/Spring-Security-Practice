package com.decoder.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //Bypass default Security implementation
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception 
	{
		
		return http.csrf(customizer -> customizer.disable())
					.authorizeHttpRequests(req ->req.anyRequest().authenticated())
//					.formLogin(Customizer.withDefaults()) //give basic form login page from Spring Security
					.httpBasic(Customizer.withDefaults()) // Enable Rest's to access this resource eg.Postman 
					.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
					// Above line makes Appliaction Stateless
					// with each request we will get new session ID 
					.build();
		
	}
	
	@Bean 
	public UserDetailsService userDetailsService()
	{
		UserDetails user1 = User.withDefaultPasswordEncoder()
								.username("Mayur")
								.password("mayur@123")
								.roles("USER")
								.build();
		
		UserDetails user2 = User.withDefaultPasswordEncoder()
				.username("Ravi")
				.password("ravi@123")
				.roles("ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(user1,user2);
	}
	

}
