package com.example.learnSecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	@Autowired
    UserDetailsService userDetailsService;
	
	@Autowired
    CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(authz -> authz
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
			)
			.authorizeHttpRequests(authz -> authz
					.requestMatchers("/img/**").permitAll()
				)
			.authorizeHttpRequests((requests) -> requests
				.anyRequest().authenticated()
			)
			.formLogin((form) -> form
				.successHandler(customAuthenticationSuccessHandler)
				.loginPage("/LearnSecurity/Login")
				.permitAll()
			)
			.logout((logout) -> logout
				.logoutUrl("/LearnSecurity/Logout")
				.logoutSuccessUrl("/LearnSecurity/Login")
				.permitAll());

		return http.build();
	}
	
	@Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
