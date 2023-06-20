package edu.pnu;

import java.util.Arrays;
import java.util.Date;
//import java.util.Date;
import java.util.List;
//import java.util.Random;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
//@TestMethodOrder(OrderAnnotation.class)
class BoardRepositoryTest {
	@Autowired 
	BoardRepository boardRepo;
	
//	@Test
//	public void testInsertBoard() {
//		Random rd = new Random();
//		for(int i = 1; i <= 100; i++) {
//			Board board = new Board();
//			board.setTitle("title"  + i);
//			board.setWriter("writer" + i);
//			board.setContent("content" +i);
//			board.setCreateDate(new Date());
//			board.setCnt(rd.nextLong(0, 101));
//			
//			boardRepo.save(board);
//		}
//		
//	}
//	
////	@Test
//	public void testGetBoard() {
//		Board board = boardRepo.findById(5L).get();
//		System.out.println(board.toString());
//	}
////	@Test	
//	public void testGetAllBoard() {
//		List<Board> list = boardRepo.findAll();
//		System.out.println("모든 데이터를 출력합니다");
//		for(Board b : list) {
//			System.out.println(b);
//		}
//	}
//	
////	@Test
//	public void testUpdateBoard() {
//		System.out.println("=== 1번 게시글 조회 ===");
//		Board board = boardRepo.findById(1L).get();
//		
//		System.out.println("=== 1번 게시글 제목 수정 ===");
//		board.setTitle("제목을 수정했습니다");
//		boardRepo.save(board);
//	}
//	
////	@Test
//	public void testDeleteBoard() {
//		boardRepo.deleteById(2L);
//	}
//	
	//@Test
	public void getBoard1() {
		List<Board> list = boardRepo.findByTitleLike("%1%");
		for(Board b : list) {
			System.out.println(b);
		}		
	}
	
	//@Test
	public void testQueryAnnotationTest1() {
		//select b from board b where b.title like "%title1%" order by b.seq desc;
		List<Board> boardList = boardRepo.queryAnnotationTest1("title1");
		
		System.out.println("검색 결과");
		for (Board b : boardList) {
			System.out.println("-->" + b.toString());
		}
	}

	//@Test
	public void testQueryAnnotationTest2() {
		List<Object[]> boardList = boardRepo.queryAnnotationTest2("title1");
		
		System.out.println("검색 결과");
		for (Object[] row : boardList) {
			System.out.println("-->" + Arrays.toString(row));
		}
	}
	
	//@Test
	public void testQueryAnnotationTest3() {
		List<Object[]> boardList = boardRepo.queryAnnotationTest3("title1");
		System.out.println("검색 결과");
		for (Object[] row : boardList) {
			System.out.println("-->" + Arrays.toString(row));
		}
	}
	
//	@Test
	public void testQueryAnnotationTest4() {
		List<Object[]> boardList = boardRepo.queryAnnotationTest4("title1");
		System.out.println("검색 결과");
		for (Object[] row : boardList) {
			System.out.println("-->" + Arrays.toString(row));
		}
	}
	
	@Test
	public void testQueryAnnotationTest5() {
		Pageable paging = PageRequest.of(0, 3, Sort.Direction.DESC, "seq");
		List<Board> list = boardRepo.queryAnnotationTest5(paging);
		
		System.out.println("검색 결과");
		for(Board b : list) {
			System.out.println("-->" + b.toString());
		}
	}
}
