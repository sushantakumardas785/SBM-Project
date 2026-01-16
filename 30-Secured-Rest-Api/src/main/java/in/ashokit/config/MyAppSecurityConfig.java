package in.ashokit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MyAppSecurityConfig {
	
	@Bean
	public PasswordEncoder pwdEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public InMemoryUserDetailsManager inMemoryUsers() {		
		
		UserDetails u1 = User.builder()
							 .username("ashok")
							 .password(pwdEncoder().encode("ashok@123"))
							 .roles("ADMIN")
							 .build();
		
		UserDetails u2 = User.builder()
							 .username("raj")
							 .password(pwdEncoder().encode("raj@123"))
							 .roles("MANAGER")
							 .build();
		
		UserDetails u3 = User.builder()
							 .username("sam")
							 .password(pwdEncoder().encode("sam@123"))
							 .roles("STUDENT")
							 .build();
		
		return new InMemoryUserDetailsManager(u1, u2, u3);
	}
	
	
	@Bean
	public SecurityFilterChain securityConfig(HttpSecurity http) throws Exception {
		
		// permit the urls which doesn't requires security
		
		// authenticate the urls which requires security
		
		http.authorizeHttpRequests(req -> 
		
					 req.requestMatchers("/public/**").permitAll()
					 
					 	.requestMatchers("/admin/**").hasRole("ADMIN")
					 	
					 	.requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
					 	
					 	.requestMatchers("/student/**").hasRole("STUDENT")
					 	
						.anyRequest().authenticated())
						.httpBasic(Customizer.withDefaults())
						.formLogin(Customizer.withDefaults());
		
		return http.build();

	}
}
