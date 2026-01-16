package in.ashokit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.clients.WelcomeApiClient;

@RestController
public class GreetRestController {
	
	@Value("${msg}")
	private String greetApiMsg;
	
	@Autowired
	private WelcomeApiClient welcomeClient;

	@GetMapping("/greet")
	public String getGreetMsg1() {
		
		/*
		
		String url = "http://localhost:8081/welcome";
		
		RestTemplate rt = new RestTemplate();
		ResponseEntity<String> forEntity = rt.getForEntity(url,  String.class);
		String welcomeApiMsg = forEntity.getBody();
		
		*/
		System.out.println("impl :: " + welcomeClient.getClass().getName());
		
		String welcomeApiMsg = welcomeClient.invokeWelcomeApi();

		return welcomeApiMsg + ", "+ greetApiMsg;
	}
	
}
