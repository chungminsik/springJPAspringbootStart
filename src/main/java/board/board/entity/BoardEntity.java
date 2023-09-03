package board.board.entity;


import jakarta.persistence.Table;
import board.board.dto.BoardDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "board_table")
public class BoardEntity extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long boardId;
	
	@Column(length=300, nullable=false)
	private String boardTitle; //제목
	
	@Column(length=2048, nullable=false)
	private String boardContents; //내용
	
	@Column
	private int boardHits; //조회수
	
	@Column(columnDefinition = "VARCHAR(1) DEFAULT 'N'" , nullable=false)
	private String deleted; //삭제 여부
	
	public static BoardEntity toSaveEntity(BoardDto boardDto) {
		BoardEntity boardEntity = new BoardEntity();
		
		boardEntity.setBoardTitle(boardDto.getBoardTitle());
		boardEntity.setBoardContents(boardDto.getBoardContents());
		boardEntity.setBoardHits(0);
		boardEntity.setDeleted("N");
		
		return boardEntity;
	}
	
	public static BoardEntity toUpdateEntity(BoardDto boardDto) {
		BoardEntity boardEntity = new BoardEntity();

		boardEntity.setBoardId(boardDto.getBoardId());
		boardEntity.setBoardTitle(boardDto.getBoardTitle());
		boardEntity.setBoardContents(boardDto.getBoardContents());
		boardEntity.setBoardHits(boardDto.getBoardHits());
		boardEntity.setDeleted("N");
		
		return boardEntity;
	}
	
	public static BoardEntity toDeleteEntity(BoardDto boardDto) {
		BoardEntity boardEntity = new BoardEntity();

		boardEntity.setBoardId(boardDto.getBoardId());
		boardEntity.setBoardTitle(boardDto.getBoardTitle());
		boardEntity.setBoardContents(boardDto.getBoardContents());
		boardEntity.setBoardHits(boardDto.getBoardHits());
		boardEntity.setDeleted("Y");
		
		return boardEntity;
	}
}
