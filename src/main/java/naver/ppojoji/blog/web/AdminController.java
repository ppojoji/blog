package naver.ppojoji.blog.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import naver.ppojoji.blog.Util;
import naver.ppojoji.blog.dto.Category;
import naver.ppojoji.blog.dto.Post;
import naver.ppojoji.blog.service.AdminService;
import naver.ppojoji.blog.service.BlogService;
import naver.ppojoji.blog.service.CategoryService;

@Controller
public class AdminController {
	@Autowired 
	AdminService adminService;
	
	@Autowired 
	BlogService blogService;
	
	@Autowired
	CategoryService cateService;
	/*
	 * FIXME 이것도 나중에 없애고 싶음
	 */
	private ObjectMapper om = new ObjectMapper();
	
	@RequestMapping(value="/admin", method = RequestMethod.GET ,produces = Value.APPLICATION_JSON_CHARSET_UTF_8)
	public String open()  {
	
		return "admin"; 
	}
	
	/*
	 * FIXME 아래의 두 메소드도 하나로 줄일 수 있습니다.
	 * 다른게 'public', private' 밖에 없음 나머지 다 똑같음
	 * @PathVariable 로 public, private 부분을 변수로 받아낼 수 있습니다.
	 * 
	 */
//	@RequestMapping(value="/posts/public", method = RequestMethod.GET ,produces = Value.APPLICATION_JSON_CHARSET_UTF_8 )
//	@ResponseBody
//	public String openPost() throws JsonProcessingException {
//		List<Post> post = blogService.readPosts(true);
//		return om.writeValueAsString(post);
//	}
//	@RequestMapping(value="/posts/private",method = RequestMethod.GET,produces = Value.APPLICATION_JSON_CHARSET_UTF_8)
//	@ResponseBody
//	public String privPost() throws JsonProcessingException {
//		List<Post> post = blogService.readPosts(false);
//		return om.writeValueAsString(post);
//	}
	@RequestMapping(value="/posts/{publicYn}", method = RequestMethod.GET ,produces = Value.APPLICATION_JSON_CHARSET_UTF_8 )
	@ResponseBody
	public String PostOpenPrivate(@PathVariable String publicYn) throws JsonProcessingException {
		boolean ispublic = "public".equals(publicYn);
		List<Post> post = null;
		if(ispublic)
		{
			 post = blogService.readPosts(true);
		}else {
			post = blogService.readPosts(false);
		}
		return om.writeValueAsString(post);
	}
	
	/**
	 * 포스트 공개/비공개 전환
	 * @param postSeq - 글번호 PK
	 * @param open - 공개('public') 비공개('private')
	 * @return
	 * @throws JsonProcessingException 
	 */
	@RequestMapping(value = "/post/{postSeq}/{open}",method = RequestMethod.POST, produces = Value.APPLICATION_JSON_CHARSET_UTF_8)
	@ResponseBody
	public String updateOpen(@PathVariable Integer postSeq,
			@PathVariable String open) throws JsonProcessingException {
		boolean isOpen = "public".equals(open);
		
		blogService.updateOpen(postSeq,isOpen);
		Map<String, Object> res = new HashMap<>();
		res.put("success", true);
		return om.writeValueAsString(res);
	}
	
	@GetMapping(value = "/categories")
	@ResponseBody
	public List<Category> cateList(HttpSession session) {
		// FIXME 로그인 정보가 있는1지 확인해야함
		return cateService.findAllCate();
	}
	
	@PostMapping(value = "/cate/deleteCate")
	@ResponseBody
	public Object deleteCate(@RequestParam Integer seq) {
		cateService.deleteCate(seq);
		return Util.success("cate", seq);
	}
}
