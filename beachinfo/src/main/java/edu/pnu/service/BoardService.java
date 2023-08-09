package edu.pnu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepo;


    
    public List<Board> getAllBoardList() {
        return boardRepo.findAll();
    }
    public void createBoard(Board board) {
        boardRepo.save(board);
    }

//    public Board getBoardByUsername(String username) {
//        return boardRepo.findById(username).orElse(null);
//    }

    public void updateBoard(Board board) {
        boardRepo.save(board);
    }

    public void deleteBoard(Integer seq) {
    	boardRepo.deleteById(seq);
    }
    
    public void deleteAllBoards() {
        // Logic to delete all boards. For instance, if you're using a repository:
        boardRepo.deleteAll();
    }
}