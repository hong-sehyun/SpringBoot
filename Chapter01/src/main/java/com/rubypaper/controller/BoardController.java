package com.rubypaper.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.domain.BoardVO;

@RestController 
public class BoardController { // extends HttpServlet같은거 사용안함 어노테이션 사용
	
	public BoardController() {//생성자
		System.out.println("=".repeat(50));
		System.out.println("=>BoardController 생성");
		System.out.println("=".repeat(50));
	}
	@GetMapping("/hello")
	public String hello1(String name) {
		return "get hello : " + name;
	}
	@PutMapping("/hello")
	public String hello2(String name) {
		return "put hello : " + name;
	}
	@PostMapping("/hello")
	public String hello3(String name) {
		return "post hello : " + name;
	}
	@DeleteMapping("/hello")
	public String hello4(String name) {
		return "delete hello : " + name;
	}
	
	@GetMapping("/getBoard")
	public BoardVO getBoard() {
		BoardVO board = new BoardVO();
		board.setSeq(1);
		board.setTitle("테스트 제목");
		board.setWriter("테스터");
		board.setContent("내용");
		board.setCreateDate(new Date());
		board.setCnt(0);
		return board;
	}
	@GetMapping("/getBoard1")
	public BoardVO getBoard1() {
		BoardVO board = new BoardVO(
		1,
		"테스트 제목",
		"테스터",
		"내용",
		new Date(),
		0);
		return board;
	}
	@GetMapping("/getBoard2")
	public BoardVO getBoard2() {
		return BoardVO.builder() //builder는 static 메서드
		.seq(1)
		.title("테스트 제목")
		.writer("테스터")
		.content("내용")
		.createDate(new Date())
		.cnt(0)
		.build();
	}
	@GetMapping("/getBoardList")
	public List <BoardVO> getBoardList() {
		List<BoardVO> getBoardList = new ArrayList<>();
		for(int i = 0; i <10; i++)
			getBoardList.add(getBoard());

		return getBoardList;
		
	}
}
