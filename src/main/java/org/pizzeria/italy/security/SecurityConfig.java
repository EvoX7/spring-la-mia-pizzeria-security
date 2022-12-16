package org.pizzeria.italy.security;

import org.pizzeria.italy.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain getFilterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests().requestMatchers("/*/user", "/*/user/**").hasAuthority("USER")
				.requestMatchers("/*/admin", "/*/admin/**").hasAuthority("ADMIN")
				.requestMatchers( "/*/useradmin", "/*/useradmin/**").hasAnyAuthority("USER", "ADMIN")
				.requestMatchers("/**").permitAll().and().formLogin().and().logout();

		return http.build();
	}

	@Bean
	public UserDetailsService getuseDetailsService() {

		return new UserService();
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {

		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider getAuthProvider() {

		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

		provider.setUserDetailsService(getuseDetailsService());
		provider.setPasswordEncoder(getPasswordEncoder());

		return provider;
	}

	@Bean
	public SpringSecurityDialect securityDialect() {
		return new SpringSecurityDialect();
	}

}
