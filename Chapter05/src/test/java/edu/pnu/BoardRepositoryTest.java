package edu.pnu;

import java.util.Date;

//import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
//@TestMethodOrder(OrderAnnotation.class)
class BoardRepositoryTest {
	@Autowired BoardRepository boardRepo;
	
	@Test
	public void testInsertBoard() {
		
		for(int i = 1; i <= 10; i++) {
			Board board = new Board();
			board.setTitle("title"  + i);
			board.setWriter("writer" + i);
			board.setContent("content" +i);
			board.setCreateDate(new Date());
			board.setCnt(0L);
			
			boardRepo.save(board);
		}
		
	}
	
	@Test
	public void testGetBoard() {
		Board board = boardRepo.findById(5L).get();
		System.out.println(board.toString());
	}
	
	@Test
	public void testUpdateBoard() {
		System.out.println("=== 1번 게시글 조회 ===");
		Board board = boardRepo.findById(1L).get();
		
		System.out.println("=== 1번 게시글 제목 수정 ===");
		board.setTitle("제목을 수정했습니다");
		boardRepo.save(board);
	}

}
