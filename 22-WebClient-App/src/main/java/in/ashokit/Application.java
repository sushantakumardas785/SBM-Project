package in.ashokit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import in.ashokit.service.ProductService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);

		ProductService bean = run.getBean(ProductService.class);

		// bean.getProductJson(1);

		// bean.getProductObj(1);

		//bean.getProductObjAsync(1);
		
		bean.addProduct();
	}

}
