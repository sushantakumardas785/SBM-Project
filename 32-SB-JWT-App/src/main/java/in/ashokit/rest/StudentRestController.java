package in.ashokit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.entity.Student;
import in.ashokit.service.JwtService;
import in.ashokit.service.StudentService;

@RestController
@RequestMapping("/api")
public class StudentRestController {

	@Autowired
	public StudentService studentService;
	
	@Autowired
	public JwtService jwtService;

	@Autowired
	private AuthenticationManager authManager;
	
	@GetMapping("/dashboard")
	public String dashboard() {
		// logic to fetch courses
		return "Core JAVA + SBMS + JRTP";
	}

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
			// if matches -> authentication success then generate JWT
			// if not matches -> throws exception

			Authentication authentication = authManager.authenticate(authToken); // login check

			if (authentication.isAuthenticated()) {
				String token = jwtService.generateToken(student.getEmail());
				return new ResponseEntity<>(token, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Invalid Credentials", HttpStatus.BAD_REQUEST);
	}
}
