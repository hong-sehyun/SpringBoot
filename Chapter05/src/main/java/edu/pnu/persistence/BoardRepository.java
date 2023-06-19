package edu.pnu.persistence;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.pnu.domain.Board;


public interface BoardRepository extends JpaRepository<Board, Long> {

	//select b from Board b where title like "%1%" 
//	List<Board> findByTitleLike(String title);
	
	List<Board> findByTitleLike(String title, Pageable paging);
}
