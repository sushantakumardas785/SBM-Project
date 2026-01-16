package in.ashokit.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import in.ashokit.binding.Product;

@Service
public class ProductService {

	public void getProductById_1(int id) {

		String url = "https://api.restful-api.dev/objects/{id}";

		RestTemplate rt = new RestTemplate();

		ResponseEntity<String> forEntity = rt.getForEntity(url, String.class, id);

		String body = forEntity.getBody();

		System.out.println(body);
	}

	public void getProductById_2(int id) {

		String url = "https://api.restful-api.dev/objects/{id}";

		RestTemplate rt = new RestTemplate();

		ResponseEntity<Product> forEntity = rt.getForEntity(url, Product.class, id);

		Product p = forEntity.getBody();

		System.out.println(p);
	}
}
