package board.board.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import board.board.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long>{
	@Modifying
	@Query(value = "update BoardEntity b set b.boardHits=b.boardHits+1 where b.boardId=:boardId")
	void updateHits(@Param("boardId") Long boardHits);
	
	List<BoardEntity> findByDeleted(String deleted);
	
	Page<BoardEntity> findByDeleted(String deleted, Pageable pageMake);
}
