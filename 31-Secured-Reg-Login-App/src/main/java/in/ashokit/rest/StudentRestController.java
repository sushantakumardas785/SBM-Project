package in.ashokit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.entity.Student;
import in.ashokit.service.StudentService;

@RestController
public class StudentRestController {

	@Autowired
	public StudentService studentService;

	@Autowired
	private AuthenticationManager authManager;

	@PostMapping("/register")
	public ResponseEntity<Student> register(@RequestBody Student student) {

		Student savedStudent = studentService.saveStudent(student);

		return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Student student) {

		UsernamePasswordAuthenticationToken authToken = 
				new UsernamePasswordAuthenticationToken(student.getEmail(), student.getPwd());
		try {

			// AuthenticationManager delegates authentication to providers
			// Authentication Provider perform actual authentication

			// calls UserDetailsService
			// loadUserRecord from DB using loadUserByUsername() method
			// compare pwd using PwdEncoder
			// if matches -> authentication success
			// if not matches -> throws exception

			Authentication authentication = authManager.authenticate(authToken); // login check

			if (authentication.isAuthenticated()) {
				// login success
				// TODO: Generate JWT Token for user
				return new ResponseEntity<>("Welcome to Ashok IT - Login success", HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Invalid Credentials", HttpStatus.BAD_REQUEST);
	}
}
