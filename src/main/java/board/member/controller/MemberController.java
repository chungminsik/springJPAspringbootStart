package board.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
	
	@GetMapping("/login")
	public String loginForm() {
		return "member/login";
	}
	
	@GetMapping("/create")
	public String createForm() {
		return "member/create";
	}
}
