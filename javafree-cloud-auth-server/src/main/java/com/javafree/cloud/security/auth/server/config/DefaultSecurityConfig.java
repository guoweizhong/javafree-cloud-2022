package com.javafree.cloud.security.auth.server.config;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/*
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2021/9/11 15:21
 * @version V1.0
 */

@EnableWebSecurity
public class DefaultSecurityConfig {

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeRequests(authorizeRequests ->
				authorizeRequests.anyRequest().authenticated()
			)
			.formLogin(withDefaults());
		return http.build();
	}
	@Bean
	UserDetailsService Users() {
		UserDetails user = User.withDefaultPasswordEncoder()
				.username("user")
				.password("123456")
				.roles("user")
				.build();
		return new InMemoryUserDetailsManager(user);
	}
}
