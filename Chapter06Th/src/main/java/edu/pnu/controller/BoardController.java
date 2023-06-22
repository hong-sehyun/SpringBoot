package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import edu.pnu.domain.Board;
import edu.pnu.service.BoardServiceImpl;

@Controller
public class BoardController {
//	@RequestMapping("/getBoardList") 
//	public String getBoardList(Model model) {
//		List<Board> boardList = new ArrayList<Board>();
//		
//		for(int i = 1; i <= 10; i++) {
//			Board board = new Board();
//			board.setSeq(new Long(i));
//			board.setTitle("게시판 프로그램 테스트");
//			board.setWriter("도우너");
//			board.setContent("테스트 입니다");
//			board.setCreateDate(new Date());
//			board.setCnt(0L);
//			boardList.add(board);
//		}
//		
//		model.addAttribute("boardList", boardList);
//		return "getBoardList";
//	}
	
	@Autowired
	private BoardServiceImpl boardService;
	
	@GetMapping("/getBoardList")
	public String getBoardList(Model model) {
		model.addAttribute("boardList", boardService.getBoardList());
		return "getBoardList";
	}
	
//	@GetMapping("/insertBoard")
//	public String insertBoard(Board board) {
//		boardService.insertBoard(board);
//		return "insertBoard";
//	}
	@GetMapping("/insertBoard")
	public String insertBoardView() {
		return "insertBoard";
	}
	
	@PostMapping("/insertBoard")
	public String insertBoard(Board board) {
		boardService.insertBoard(board);
		return "redirect:getBoardList";
	}
	
	@GetMapping("/getBoard")
	public String getBoard(Long seq, Model model) {
		Board board = boardService.getBoard(Board.builder().seq(seq).build());
		model.addAttribute("board", board);
		return "getBoard";
	}
	
	@PostMapping("/updateBoard")
	public String updateBoard(Board board) {
		boardService.updateBoard(board);
		return "forward:getBoardList";
	}
	
	public String deleteBoard(Board board) {
		boardService.deleteBoard(board);
		return "forward:getBoardList";
	}
}
