package edu.pnu.controller;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import edu.pnu.domain.Member;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoginController {
	
	private final AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public ResponseEntity<?> loginPost(@RequestBody Member member ) {
		
		System.out.println("member:" + member);
		
		Authentication authToken = new UsernamePasswordAuthenticationToken(member.getUsername(), member.getPassword());
		// authToken을 사용하여 사용자를 인증하고 인증 성공시 Authentication 객체 반환
		Authentication auth = authenticationManager.authenticate(authToken);
		
		User user = (User)auth.getPrincipal();
		
		// JWT 생성
		String jwtToken = JWT.create()
				.withClaim("username", user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis()+1000*60*10))
				.sign(Algorithm.HMAC256("edu.pnu.jwtkey"));
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization","Bearer" + jwtToken);
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<>("OK", headers, HttpStatus.OK);
		
	}

	@GetMapping("/test")
	public String Test() {
		return "test";
	}
}
