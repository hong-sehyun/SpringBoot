package edu.pnu.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.back.AuthenticationFilter;

import edu.pnu.service.JwtService;
import edu.pnu.service.UserDetailsServiceImpl;

//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//	
////	 @Autowired
////	 @Qualifier("authenticationManagerBean")
////	 private AuthenticationManager authenticationManager;
//	
//	
////	@Autowired
////	private AuthenticationConfiguration authConfig;
//	
//	@Autowired
//	private MemberRepository memberRepository;
//
//	@Bean
//	public PasswordEncoder bCryptPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	@Bean
//	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//		return authenticationConfiguration.getAuthenticationManager();
//	}
//	
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//		http.csrf(csrf->csrf.disable());
////		http.cors(cors->cors.disable());
//		
//		http.authorizeHttpRequests(security->{
//			security.requestMatchers("/member/**").authenticated()
//			.requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
//			.requestMatchers("/admin/**").hasRole("ADMIN")
//			.anyRequest().permitAll();
//		});
//		
//		
//		http.formLogin(frmLogin->frmLogin.disable()); //form 을 이용한 로그인을 사용하지 않겠다는 뜻
//		
//		http.sessionManagement(ssmg->ssmg.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//		
////		AuthenticationManager auth = authConfig.getAuthenticationManager();
////		System.out.println("auth :"+auth);
//		//JWTAuthenticationFilter의 인증 메소드 / 필터를 안써도ㅜ 됨 
////		http.addFilter(new JWTAuthenticationFilter(auth));
////		
//		http.addFilterBefore(new JWTAuthorizationFilter(memberRepository), BasicAuthenticationFilter.class);
////		
////		http.authorizeHttpRequests(auth->{
////			auth.anyRequest().permitAll();
////		});
//		
//		
//	
//		return http.build();
//	}
//	
//}
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private JwtService jwtService;
	
	//무한루프 수정
    @Autowired
    UserDetailsServiceImpl userDetailsService;

//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new UserDetailsServiceImpl();
//    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.csrf(abcd->abcd.disable());
		http.sessionManagement(abcd->abcd.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		
		http.authorizeHttpRequests(security->{
			security.requestMatchers("/member/**").authenticated()
			.requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
			.requestMatchers("/admin/**").hasRole("ADMIN")
			.anyRequest().permitAll();
		});
		http.addFilterBefore(new AuthenticationFilter(jwtService), 
				UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}	

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("*"));
		config.setAllowedMethods(Arrays.asList("*"));
		config.setAllowedHeaders(Arrays.asList("*"));
		config.setAllowCredentials(false);
		config.applyPermitDefaultValues();

		source.registerCorsConfiguration("/**", config);
		return source;
	}	

//	@Bean
//	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
//		return authConfig.getAuthenticationManager();
//	}
	

	
	 @Bean
     public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
         AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
         authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
         return authenticationManagerBuilder.build();
     }

}