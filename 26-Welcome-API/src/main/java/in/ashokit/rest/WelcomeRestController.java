package in.ashokit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class WelcomeRestController {

	@Value("${msg}")
	private String msg;

	@Autowired
	private Environment env;

	@GetMapping("/welcome")
	public String getWelcomeMsg() {

		String port = env.getProperty("server.port");

		return msg + "(" + port + ")";
	}
}
