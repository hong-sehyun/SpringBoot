package edu.pnu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.service.TestService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TestController {
//	@Autowired
//	private TestService testService;
//	
//	public TestController() {
//		System.out.println("TestController");
//	}

	
	private final TestService test;
//	@Autowired
//	public void TestService(TestService test) {
//		this.test = test;
//		System.out.println("TestController");
//	}
	@GetMapping({"/", "/home"})
	public String home() {
		return "home";
	}
}
