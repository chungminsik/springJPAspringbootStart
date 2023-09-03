package board.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import board.board.dto.BoardDto;
import board.board.entity.BoardEntity;
import board.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardRepository boardRepository;
	
	@Transactional
	public List<BoardDto> findAll(){
		List<BoardEntity> boardEntityList = boardRepository.findByDeleted("N");
		List<BoardDto> boardDtoList = new ArrayList<>();
		for(BoardEntity boardEntity : boardEntityList) {
			boardDtoList.add(BoardDto.toBoardDto(boardEntity));
		}
		
		return boardDtoList;
	}
	
	@Transactional
	public Page<BoardDto> pagingList(Pageable pageable){
		int page = pageable.getPageNumber() -1;
		int pageLimit = 10;
		Pageable pageMake = PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "boardId"));
		Page<BoardEntity> boardEntities = boardRepository.findByDeleted("N", pageMake);
		
		Page<BoardDto> boardDtos = boardEntities.map(board -> new BoardDto(board.getBoardId(), board.getBoardTitle(), board.getBoardContents(), board.getBoardHits(), board.getDeleted(), board.getBoardCreatedTime()));
		return boardDtos;
	}
	
	
	
	public void save(BoardDto boardDto) {
		BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDto);
		boardRepository.save(boardEntity);
	}
	
	@Transactional
	public void updateHits(Long boardId) {
		boardRepository.updateHits(boardId);
	}
	
	public BoardDto findById(Long boardId) {
		Optional<BoardEntity> boardEntity = boardRepository.findById(boardId);
		if (boardEntity.isPresent()) {
			BoardDto boardDto = BoardDto.toBoardDto(boardEntity.get());
			return boardDto;
		}
		else {
			return null;
		}
	}
	
	public void update(BoardDto boardDto) {
		BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDto);
		boardRepository.save(boardEntity);
	}
	
	public void delete(Long boardId) {
		BoardDto boardDto = findById(boardId);
		BoardEntity boardEntity = BoardEntity.toDeleteEntity(boardDto);
		boardRepository.save(boardEntity);
	}
}
