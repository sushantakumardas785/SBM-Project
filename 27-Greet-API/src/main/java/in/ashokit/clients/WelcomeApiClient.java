package in.ashokit.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="welcome")
public interface WelcomeApiClient {	
	
	@GetMapping("/welcome")
	public String invokeWelcomeApi();

}
