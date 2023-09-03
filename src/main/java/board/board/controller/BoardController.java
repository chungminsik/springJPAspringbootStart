package board.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import board.board.dto.BoardDto;
import board.board.service.BoardService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
	
	@GetMapping("/board/save")
	public String saveForm() {
		return"board/save";
	}
	
	@PostMapping("/board/save")
	public String save(@ModelAttribute BoardDto boardDto) {
		System.out.println("boardDto : "+boardDto);
		boardService.save(boardDto);
		
		return "redirect:/";
	}
	
	@GetMapping("/board/detail/{boardId}")
	public String detailForm(@PathVariable Long boardId, Model model) {
		boardService.updateHits(boardId);
		BoardDto board = boardService.findById(boardId);
		model.addAttribute("boardDetail", board);
		
		return "board/detail";
	}
	
	@GetMapping("/update/{boardId}")
	public String updateForm(@PathVariable Long boardId, Model model) {
		BoardDto board = boardService.findById(boardId);
		model.addAttribute("boardUpdate", board);
		return "board/update";
	}
	
	@GetMapping("/board/update")
	public String update(@ModelAttribute BoardDto boardDto) {
		boardService.update(boardDto);
		
		return "redirect:/";
	}
	
	@GetMapping("/delete/{boardId}")
	public String delete(@PathVariable Long boardId) {
		boardService.delete(boardId);
		
		return "redirect:/";
	}
}
