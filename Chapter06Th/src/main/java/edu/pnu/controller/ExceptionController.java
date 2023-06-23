package edu.pnu.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.pnu.exception.BoardNotFoundException;
import edu.pnu.exception.UsernameNotFoundException;

@Controller
public class ExceptionController {
	@RequestMapping("/boardError")
	public String boardError() {
		throw new BoardNotFoundException("검색된 게시글 없음");
	}
	
	
	@RequestMapping("/illegalArgumentException")
	public String ellegalArgumentError() {
		throw new IllegalArgumentException("부적절한 인자 전달");
	}
	
	@RequestMapping("/sqlError")
	public String sqlError() throws SQLException {
		throw new SQLException("sql 구문 오류");
	}
	
	@ExceptionHandler(SQLException.class) 
	public String numberFormatError(SQLException exception, Model model) {
		model.addAttribute("exception", exception);
		return "/errors/sqlError";
	}
	
//	@ExceptionHandler(SQLException.class) 
//	public String UsernameNotFoundException(SQLException exception, Model model) {
//		model.addAttribute("exception", exception);
//		return "/errors/loginError";
//	}
	
}
