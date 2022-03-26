package naver.ppojoji.blog.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import naver.ppojoji.blog.Util;
import naver.ppojoji.blog.dto.Post;
import naver.ppojoji.blog.dto.User;
import naver.ppojoji.blog.service.BookMarkService;

@Controller
public class BookMarkController {
	
	@Autowired
	BookMarkService bookMarkService;
	
	/**
	 * 로그인한 사용자의 북마크 글들을 반환
	 * @param session
	 * @return
	 */
	@GetMapping("/api/bookmarks")
	@ResponseBody
	public Object loadBookMarks(HttpSession session) {
		User adminUser = Util.getUser(session);
		List<Post> posts = bookMarkService.loadBookMarks(adminUser.getSeq());
		return posts;
	}
}
