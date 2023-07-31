package edu.pnu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import edu.pnu.config.filter.JWTAuthorizationFilter;
import edu.pnu.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;

//import edu.pnu.service.UserService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
//	 @Autowired
//	 @Qualifier("authenticationManagerBean")
//	 private AuthenticationManager authenticationManager;
	
	
//	@Autowired
//	private AuthenticationConfiguration authConfig;
	
	@Autowired
	private MemberRepository memberRepository;

	@Bean
	public PasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf->csrf.disable());
//		http.cors(cors->cors.disable());
		
		http.authorizeHttpRequests(security->{
			security.requestMatchers("/member/**").authenticated()
			.requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
			.requestMatchers("/admin/**").hasRole("ADMIN")
			.anyRequest().permitAll();
		});
		
		
		http.formLogin(frmLogin->frmLogin.disable()); //form 을 이용한 로그인을 사용하지 않겠다는 뜻
		
		http.sessionManagement(ssmg->ssmg.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
//		AuthenticationManager auth = authConfig.getAuthenticationManager();
//		System.out.println("auth :"+auth);
		//JWTAuthenticationFilter의 인증 메소드 / 필터를 안써도ㅜ 됨 
//		http.addFilter(new JWTAuthenticationFilter(auth));
//		
		http.addFilterBefore(new JWTAuthorizationFilter(memberRepository), BasicAuthenticationFilter.class);
//		
//		http.authorizeHttpRequests(auth->{
//			auth.anyRequest().permitAll();
//		});
		
		
	
		return http.build();
	}
	
}