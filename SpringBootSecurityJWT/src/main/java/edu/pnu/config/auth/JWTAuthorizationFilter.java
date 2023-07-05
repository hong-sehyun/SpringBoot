package edu.pnu.config.auth;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter{
	// 사용자 정보 읽기
	private MemberRepository memRepo;
	
	// 인증 정보를 확인하고 인가
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, MemberRepository memRepo) {
		super(authenticationManager);
		this.memRepo = memRepo;
	}
	
	
	// 실제 필터링 작업 수행
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) 
			throws IOException, ServletException {
		String srcToken = req.getHeader("Authorization");
		if(srcToken == null || !srcToken.startsWith("Bearer")) {
			chain.doFilter(req, resp);
			return;
		}
		
		String jwtToken = srcToken.replace("Bearer ", "");
		String username = JWT.require(Algorithm.HMAC256("edu.pnu.jwtkey")).build().verify(jwtToken).getClaim("username").asString();
		// 토큰에서 얻은 username으로 사용자 찾음
		Optional<Member> opt = memRepo.findById(username);
		if(!opt.isPresent()) {
			chain.doFilter(req, resp);
			return;
		}
		
		Member findmember = opt.get();
		User user =  new User(findmember.getUsername(), findmember.getPassword(), findmember.getAuthorities());
		// Authentication 객체 생성, 암호는 필요없음
		Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
		chain.doFilter(req, resp);
	}
}
