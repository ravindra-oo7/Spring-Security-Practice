package com.decoder.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity //Bypass default Security implementation
public class SecurityConfig {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtFilter jwtFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception 
	{
		
		return http.csrf(customizer -> customizer.disable())
					.authorizeHttpRequests(req ->req
							.requestMatchers("register","login").permitAll()
							.anyRequest().authenticated())
//					.formLogin(Customizer.withDefaults()) //give basic form login page from Spring Security
					.httpBasic(Customizer.withDefaults()) // Enable Rest's to access this resource eg.Postman 
					.sessionManagement(session -> 
						session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
					// Above line makes Appliaction Stateless
					// with each request we will get new session ID
					.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
					.build();
		
	}
	
//This is Usefull to have One ADMIN which will be Hard coded
//	@Bean 
//	public UserDetailsService userDetailsService()
//	{
//		UserDetails user1 = User.withDefaultPasswordEncoder()
//								.username("Mayur")
//								.password("mayur@123")
//								.roles("USER")
//								.build();
//		
//		UserDetails user2 = User.withDefaultPasswordEncoder()
//				.username("Ravi")
//				.password("ravi@123")
//				.roles("ADMIN")
//				.build();
//		
//		return new InMemoryUserDetailsManager(user1,user2);
//	}
	
	@Bean
	public AuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		provider.setUserDetailsService(userDetailsService);
		
		return provider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
	{
		return config.getAuthenticationManager();
		
	}

	
	

}
