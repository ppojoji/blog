package naver.ppojoji.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
	
	@GetMapping(value="/posts/{cateName}")
	public String pageCategory(@PathVariable String cateName) {
		return "index";
	}
	
	@GetMapping(value="/article/{postSeq}")
	public String pageCategory(@PathVariable Integer postSeq) {
		return "index";
	}
	
	@GetMapping(value="/me")
	public String myPage() {
		return "index";
	}
}
