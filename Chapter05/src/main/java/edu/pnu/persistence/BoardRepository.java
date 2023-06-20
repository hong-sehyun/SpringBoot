package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.pnu.domain.Board;


public interface BoardRepository extends JpaRepository<Board, Long> {

	//select b from Board b where title like "%1%" 
	List<Board> findByTitleLike(String title);
	
	List<Board> findByTitleLike(String title, Pageable paging);
	
	@Query("select b from Board b where b.title like %?1% order by b.seq desc")
	List<Board> queryAnnotationTest1(String searchKeyword);
	
	@Query("select b from Board b where b.title like %:searchKeyword% order by b.seq desc")
	List<Object[]> queryAnnotationTest2(String searchKeyword);
	
	@Query("select b.seq, b.title, b.writer, b.createDate from Board b where b.title like %:searchKeyword% order by b.seq desc")
	List<Object[]> queryAnnotationTest3(String searchKeyword);
	
	@Query(value="select seq, title, writer, create_date from board where title like '%'||:sk||'%' order by seq desc", nativeQuery=true)
	List<Object[]> queryAnnotationTest4(@Param("sk") String searchKeyword);
	
	@Query("select b from Board b order by b.seq desc")
	List<Board> queryAnnotationTest5(Pageable paging);
}
