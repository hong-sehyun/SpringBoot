package edu.pnu;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;
import edu.pnu.persistence.MemberRepository;
import edu.pnu.service.BoardService;

@SpringBootTest
@SpringBootApplication
class TestApplicationTests {

	@Autowired
	private MemberRepository memRepo;
	
	@Autowired
	private BoardRepository boardRepo;
	
	@Autowired
	private BoardService boardService;

//	@Test
//	void contextLoads() {
//		
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		
//		memRepo.save(Member.builder().username("member").password(encoder.encode("abcd")).role("ROLE_MEMBER").enabled(true).build());
//	}

	
	@Test
	public void testInsertBoard() {
		Board board = new Board();
		board.setUsername("kim1");
		board.setBeach("해운대");
		board.setContent("테스트 등록");
		board.setCreateDate(new Date());
		
		boardService.createBoard(board);
	}
	

}
