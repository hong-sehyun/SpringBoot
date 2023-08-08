package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @PostMapping
    public void createtBoard(@RequestBody Board board) {
        boardService.createBoard(board);
    }

//    @GetMapping("/{username}")
//    public Board getBoardByUsername(@AuthenticationPrincipal String username) {
//        return boardService.getBoardByUsername(username);
//    }
//
//    @PutMapping("/{username}")
//    public void updateBoard(@PathVariable String username, @RequestBody Board board) {
//        Board findBoard = boardService.getBoardByUsername(username);
//        findBoard.setContent(board.getContent());
//        boardService.updateBoard(findBoard);
//    }

    @DeleteMapping("/{seq}")
    public void deleteBoard(@PathVariable Integer seq, @AuthenticationPrincipal Board board) {
        boardService.deleteBoard(seq);
    }
}