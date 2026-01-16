package in.ashokit.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import in.ashokit.service.JwtService;
import in.ashokit.service.StudentService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class TokenValidationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtService jwtService;

	@Autowired
	private StudentService studentService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String headerValue = request.getHeader("Authorization");
		
		String token = null;
		String email = null;

		if (headerValue != null && !headerValue.equals("") && headerValue.startsWith("Bearer")) {
			token = headerValue.substring(7);
			email = jwtService.extractUsername(token);
		}
		
		if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDtls = studentService.loadUserByUsername(email);
			if(jwtService.validateToken(token, userDtls)) {
				
				UsernamePasswordAuthenticationToken authToken = 
						new UsernamePasswordAuthenticationToken(userDtls, null, userDtls.getAuthorities());
				
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		filterChain.doFilter(request, response);
	}
}
