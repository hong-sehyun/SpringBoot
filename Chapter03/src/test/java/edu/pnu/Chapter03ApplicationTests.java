package edu.pnu;

import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
//@WebMvcTest
class Chapter03ApplicationTests {
	
//	@Autowired
//	MockMvc mocmvc;
	
	@Test
	void contextLoads() throws Exception {
//		mocmvc.perform(get("/hello").param("name", "둘리"))
//		.andExpect(status().isOk())
////		.andExpect(content().string("Hello : 둘리"))
//		.andDo(print());
		System.out.println("contextLoads");
	}

}
