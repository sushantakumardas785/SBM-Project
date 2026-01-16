package in.ashokit.filter;

import java.util.List;
import java.util.Set;

import org.bouncycastle.crypto.RuntimeCryptoException;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class RequestValidationFilter implements GlobalFilter {

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

		System.out.println("filter () called...");

		// Logic to validate the request

		ServerHttpRequest request = exchange.getRequest();

		HttpHeaders headers = request.getHeaders();

		Set<String> keySet = headers.keySet();

		if (!keySet.contains("SecretToken")) {
			throw new RuntimeException("Invalid Request");
		}

		List<String> list = headers.get("SecretToken");
		if (!list.get(0).equals("ashokit@123")) {
			throw new RuntimeException("Invalid Request");
		}

		return chain.filter(exchange);
	}

}
