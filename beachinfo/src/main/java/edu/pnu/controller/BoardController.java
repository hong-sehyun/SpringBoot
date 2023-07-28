package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Board;
import edu.pnu.service.BoardService;

@RestController
@RequestMapping("/Boards")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping
    public List<Board> getAllBoardList() {
        return boardService.getAllBoardList();
    }
//    @GetMapping
//    public String boardList(Model model) {
//        List<Board> boardList = boardService.getAllBoardList();
//        model.addAttribute("boardList", boardList);
//        return "board";
//    }

    @PostMapping
    public void createtBoard(@RequestBody Board board) {
        boardService.createBoard(board);
    }

    @GetMapping("/{id}")
    public Board getBoardById(@PathVariable String id) {
        return boardService.getBoardById(id);
    }

    @PutMapping("/{id}")
    public void updateBoard(@PathVariable String id, @RequestBody Board board) {
        Board findBoard = boardService.getBoardById(id);
        findBoard.setContent(board.getContent());
        boardService.updateBoard(findBoard);
    }

    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable String id) {
        boardService.deleteBoard(id);
    }
}