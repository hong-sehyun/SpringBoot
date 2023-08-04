package edu.pnu;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;
import edu.pnu.service.BoardService;

@SpringBootTest
class BeachinfoApplicationTests {
//	@Autowired
//	private MemberRepository memRepo;
//
//	
//	@Autowired
//	private BCryptPasswordEncoder encoder;
	
	
	
	@Test
	void contextLoads() {
//		memRepo.save(Member.builder().username("member").password(encoder.encode("abcd")).role("ROLE_MEMBER").build());
		System.out.println("Test");
	}
	
	
	@Autowired
    private BoardService boardService;


    @Test
    public void testCreateBoard() {
    	
        Board newBoard = new Board();
        newBoard.setUsername("lee");
        newBoard.setBeach("가계");
        newBoard.setContent("테스트 입력 예시");
        boardService.createBoard(newBoard);

    }

}
