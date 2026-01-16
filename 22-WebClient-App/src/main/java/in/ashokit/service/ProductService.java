package in.ashokit.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import in.ashokit.binding.Data;
import in.ashokit.binding.Product;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
	
	private String apiUrl = "https://api.restful-api.dev/objects/{id}";
	
	private String addProductURL = "https://api.restful-api.dev/objects";
	
	public void getProductJson(int id) {
		
		WebClient webClient = WebClient.create(); // it will give impl cls obj
		
		  Mono<String> bodyToMono = webClient.get()
											 .uri(apiUrl, 1)
											 .retrieve()
											 .bodyToMono(String.class);
		  
		String block = bodyToMono.block(); //sync call
		System.out.println(block);
	}
	
	public void getProductObj(int id) {
		
		WebClient webClient = WebClient.create(); // it will give impl cls obj
		
		  Mono<Product> bodyToMono = webClient.get()
											 .uri(apiUrl, 1)
											 .retrieve()
											 .bodyToMono(Product.class);
		  
		  Product p = bodyToMono.block(); //sync call
		  
		  System.out.println(p);
	}
	
	public void getProductObjAsync(int id) {
		
		WebClient webClient = WebClient.create(); // it will give impl cls obj
		
		    webClient.get()
					 .uri(apiUrl, 1)
					 .retrieve()
					 .bodyToMono(Product.class)
					 .subscribe(response -> { // async call
						 handleResponse(response);
					 });	  
		 		  
		System.out.println("========= Request sending completed =============");
	}
	
	
	private void handleResponse(Product p) {
		System.out.println(p);
	}
	
	
	public void addProduct() {
		
		Data d = new Data();
		d.setColor("Blue");
		d.setCapacity("512 GB");
		
		Product p = new Product();
		p.setName("Apple 19 Pro");
		p.setData(d);
		
		WebClient webClient = WebClient.create(); // it will give impl cls obj
		
		  Mono<String> bodyToMono = webClient.post()
											 .uri(addProductURL)
											 .header("Content-Type", "application/json")
											 .header("Accept", "application/json")
											 .body(BodyInserters.fromValue(p)) // payload in req body
											 .retrieve()
											 .bodyToMono(String.class);
		  String block = bodyToMono.block();
		  System.out.println(block);
	}
}
