package in.ashokit.config;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import in.ashokit.filter.TokenValidationFilter;
import in.ashokit.service.StudentService;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private TokenValidationFilter filter;

	@Bean
	public PasswordEncoder pwdEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authProvider() {

		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(studentService);
		authProvider.setPasswordEncoder(pwdEncoder());

		return authProvider;

	}

	@Bean
	public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	
	@Bean
	public SecurityFilterChain security(HttpSecurity http) throws Exception {

	    return http
	        .csrf(csrf -> csrf.disable())

	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/api/register", "/api/login").permitAll()
	            .requestMatchers("/api/**").authenticated()
	            //.requestMatchers("/api/admin/**").hasRole("ADMIN")
	        )

	        .sessionManagement(session -> 
	            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        )

	        .authenticationProvider(authProvider())

	        .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)

	        .build();
	}

}
