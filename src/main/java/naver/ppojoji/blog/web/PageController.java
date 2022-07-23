package naver.ppojoji.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

	@GetMapping(value="/login")
	public String login() {
		return "index";
	}
	
	@GetMapping(value="/join")
	public String join() {
		return "index";
	}
	
	@GetMapping(value="/tags")
	public String tags() {
		return "index";
	}
}
