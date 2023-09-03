package board.board.dto;

import java.time.LocalDateTime;

import board.board.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
	private Long boardId; //글 번호
	private String boardTitle; //제목
	private String boardContents; //내용
	private int boardHits; //조회수
	private LocalDateTime boardCreatedTime; //만든 시간
	private LocalDateTime boardUpdateTime; //수정한 시간
	private String deleted; //삭제 여부
	
	public static BoardDto toBoardDto(BoardEntity boardEntity) {
		BoardDto boardDto = new BoardDto();
		
		boardDto.setBoardId(boardEntity.getBoardId());
		boardDto.setBoardTitle(boardEntity.getBoardTitle());
		boardDto.setBoardContents(boardEntity.getBoardContents());
		boardDto.setBoardHits(boardEntity.getBoardHits());
		boardDto.setBoardCreatedTime(boardEntity.getBoardCreatedTime());
		boardDto.setBoardUpdateTime(boardEntity.getBoardUpdateTime());
		boardDto.setDeleted(boardEntity.getDeleted());
		
		return boardDto;
	}
	
	public BoardDto(Long boardId, String boardTitle, String boardContents, int boardHits, String deleted, LocalDateTime boardCreatedTime) {
		this.boardId = boardId;
		this.boardTitle = boardTitle;
		this.boardContents = boardContents;
		this.boardHits = boardHits;
		this.deleted = deleted;
		this.boardCreatedTime = boardCreatedTime;
	}
}
