package edu.pnu.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
	
	public TestConfig() {
		System.out.println("=".repeat(50));
		System.out.println("=>TestConfig 생성");
		System.out.println("=".repeat(50));
	}
	@Bean
	public TestBean testBean() {
		return new TestBean();
	}
}

class TestBean {
	public TestBean() {
		System.out.println("=".repeat(50));
		System.out.println("=>TestBean 생성");
		System.out.println("=".repeat(50));
	}
}