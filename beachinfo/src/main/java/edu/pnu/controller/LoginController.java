package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Member;
import edu.pnu.service.JwtService;

@RestController
//@RequiredArgsConstructor
public class LoginController {
	
//	private final AuthenticationManager authenticationManager;
//
//	@PostMapping("/login")
//	public ResponseEntity<?> loginPost(@RequestBody Member member ) {
//		
//		System.out.println("member:" + member);
//		
//		Authentication authToken = new UsernamePasswordAuthenticationToken(member.getUsername(), member.getPassword());
//		// authToken을 사용하여 사용자를 인증하고 인증 성공시 Authentication 객체 반환
//		Authentication auth = authenticationManager.authenticate(authToken);
//		
//		User user = (User)auth.getPrincipal();
//		
//		// JWT 생성
//		String jwtToken = JWT.create()
//				.withClaim("username", user.getUsername())
//				.withExpiresAt(new Date(System.currentTimeMillis()+1000*60*10))
//				.sign(Algorithm.HMAC256("edu.pnu.jwtkey"));
//		
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Authorization","Bearer" + jwtToken);
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		return new ResponseEntity<>("OK", headers, HttpStatus.OK);
//		
//	}
//
//	@GetMapping("/test")
//	public String Test() {
//		return "test";
//	}
	
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public ResponseEntity<?> getToken(@RequestBody Member member) {
		
		System.out.println("member : " + member);
		UsernamePasswordAuthenticationToken creds =
				new UsernamePasswordAuthenticationToken(
						member.getUsername(), 
						member.getPassword());	

		Authentication auth = authenticationManager.authenticate(creds);

		// Generate token
		String jwts = jwtService.getToken(auth.getName());

		// Build response with the generated token
		return ResponseEntity.ok()
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + jwts)
				.header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
				.build();
		

	}
}
