package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

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

//    @PostMapping
//    public void createtBoard(@RequestBody Board board) {
//        boardService.createBoard(board);
//    }

    @PostMapping
    public ResponseEntity<?> createBoard(@RequestParam("username") String username,
                                         @RequestParam("beach") String beach,
                                         @RequestParam("content") String content,
                                         @RequestParam("image") MultipartFile image) {
        
        Board board = new Board();
        board.setUsername(username);
        board.setBeach(beach);
        board.setContent(content);

        // You can save the image to a directory and store its path in the database, or handle as needed.
        // For instance:
        String imagePath = saveImage(image); // This function should handle image saving logic.
        board.setImagePath(imagePath);

        boardService.createBoard(board);
        return ResponseEntity.ok("Board and Image uploaded successfully!");
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
    
    
    
    private boolean isAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null && auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
    @DeleteMapping
    public void deleteAllBoards(@AuthenticationPrincipal String username) {
        // Check if user is admin
        if (isAdmin()) {
            boardService.deleteAllBoards();
        } else {
            throw new ResponseStatusException(
              HttpStatus.FORBIDDEN, "Not authorized to delete all boards"
            );
        }
    }
}