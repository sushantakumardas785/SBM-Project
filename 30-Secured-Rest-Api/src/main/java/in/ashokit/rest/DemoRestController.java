package in.ashokit.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoRestController {
	
	
	@GetMapping("/super-admin/home")
	public String superAdminPage() {
		return "This is Admin Home Page";
	}

	@GetMapping("/admin/home")
	public String adminHome() {
		return "This is Admin Home Page";
	}

	@GetMapping("/manager/home")
	public String managerHome() {
		return "This is manager Home Page";
	}

	@GetMapping("/student/home")
	public String studentHome() {
		return "This is Student Home Page";
	}

	@GetMapping("/public/contact")
	public String getContactInfo() {
		return "Please call us on +91-9985396677";
	}

	@GetMapping("/public/about")
	public String about() {
		return "Ashok IT - Software Training institute (Hyd based)";
	}

}
