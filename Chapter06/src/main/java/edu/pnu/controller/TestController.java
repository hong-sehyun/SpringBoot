package edu.pnu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	@GetMapping("/home")
	public String home() {
		//WEB-INF/board/home.jsp 호출
		return "home";
	}
}
