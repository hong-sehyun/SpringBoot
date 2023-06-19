package edu.pnu.controller;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@RestController
public class BoardController {
	@Autowired BoardRepository boardRepo;
	@GetMapping("/getBoardList")
	public List<Board> getBoardList() {
		return boardRepo.findAll();
	}
	@PostMapping("/insertBoard")
	public Board insertBoard(Board board) {
		return boardRepo.save(board);
	}
	@PutMapping("/board") 
	public Board updateBoard(Board board) {
		return boardRepo.save(board);
	}
	
	@GetMapping("/board")
	public Board getBoard(Long id) {
		return boardRepo.findById(id).get();
	}
	@DeleteMapping("/board")
	public String deleteBoard(Long id) {
		boardRepo.deleteById(id);
		return String.format("%d 가 사라졌습니다", id);
	}
	
	@GetMapping("/findByTitleLike")
	public List<Board> findByTitleLike(String title) {
		Pageable page = PageRequest.of(0, 5);
		return boardRepo.findByTitleLike("%" + title + "%", page);
	}

}
