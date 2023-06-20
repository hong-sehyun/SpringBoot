package edu.pnu.persistence;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.Repository;

import edu.pnu.domain.Board;

public interface DynamicBoardRepository extends QuerydslPredicateExecutor<Board>, Repository<Board, Long> {

}
