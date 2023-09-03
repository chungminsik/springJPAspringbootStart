package board.board.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import board.board.dto.BoardDto;
import board.board.service.BoardService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	
	private final BoardService boardService;
	/*
	@GetMapping("/")
	public String list(Model model) {
		List<BoardDto> boardList = boardService.findAll();
		model.addAttribute("boardList", boardList);
		
		return "list";
	}
	*/
	@GetMapping("/")
	public String list2(@PageableDefault(page = 1) Pageable pageable, Model model) {
		Page<BoardDto> boardList = boardService.pagingList(pageable);
		int blockLimit = 10;
		int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
		int endPage = ((startPage + blockLimit - 1) < boardList.getTotalPages()) ? startPage + blockLimit - 1 : boardList.getTotalPages();// 3 6 9 12 ~~
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		return "board/list";
	}
}
