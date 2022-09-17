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
import naver.ppojoji.blog.dto.BanRepoter;
import naver.ppojoji.blog.dto.Category;
import naver.ppojoji.blog.dto.Post;
import naver.ppojoji.blog.dto.User;
import naver.ppojoji.blog.service.AdminService;
import naver.ppojoji.blog.service.BanReporterService;
import naver.ppojoji.blog.service.BlogService;
import naver.ppojoji.blog.service.CategoryService;

@Controller
public class AdminController {
	@Autowired 
	AdminService adminService;
	
	@Autowired
	BanReporterService banService;
	
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
		// FIXME 로그인 정보가 있는지 확인해야함
		return cateService.findAllCate();
	}
	
	@PostMapping(value = "/cate/deleteCate")
	@ResponseBody
	public Object deleteCate(@RequestParam Integer seq) {
		cateService.deleteCate(seq);
		return Util.success("cate", seq);
	}
	
	@GetMapping(value = "/admin/api/ban/post")
	@ResponseBody
	public Object banPostList(HttpSession session) {
		return banService.getBanPostList();
	}
	
	@GetMapping(value = "/admin/api/ban/reply")
	@ResponseBody
	public Object banReplyList(HttpSession session) {
		return banService.getBanReplyList();
	}
	/*
	@PostMapping(value = "/admin/api/ban/{banSeq}/approve")
	@ResponseBody
	public Object banHandle(HttpSession session,@PathVariable Integer banSeq) {
		System.out.println("[ban seq: ] " + banSeq);
		User adminUser = Util.getUser(session);
		BanRepoter ban = banService.processBan(adminUser, banSeq, true);
		
		if(ban.getTargetType().equals("P")) {
			return banService.getBanPostList();
		}else {
			return banService.getBanReplyList();
		}
	}
	@PostMapping(value = "/admin/api/ban/{banSeq}/reject")
	@ResponseBody
	public Object banRejectHandle(HttpSession session,@PathVariable Integer banSeq) {
		System.out.println("[ban seq: ] " + banSeq);
		User adminUser = Util.getUser(session);
		banService.processBan(adminUser, banSeq, false);
		return null;
		// return banService.getBanReplyList();
	}
	*/
	@PostMapping(value = "/admin/api/ban/{banSeq}/{decision}")
	@ResponseBody
	public Object banHandle(HttpSession session,@PathVariable Integer banSeq,
			@PathVariable String decision) {
		System.out.println("[ban seq: ] " + banSeq);
		User adminUser = Util.getUser(session);
		
		boolean approve; 
		if(decision.equals("approve")) {
			approve = true;
		}else if(decision.equals("reject")){
			approve = false;
		}else {
			// FIXME 애플리케이션 예외로 나중에 바꿔야 함
			throw new RuntimeException("!!!!!!!!");
		}
		
		BanRepoter ban = banService.processBan(adminUser, banSeq, approve);
		
		return ban;
//		if(ban.getTargetType().equals("P")) {
//			return banService.getBanPostList();
//		}else {
//			return banService.getBanReplyList();
//		}
	}
	
	@GetMapping(value = "/admin/api/ban/badUser")
	@ResponseBody
	public List<User> badUser() {
		List<User> list = banService.badUser();
		return list;
	}
	
	@GetMapping(value = "/admin/api/ban/detail/{banUserSeq}")
	@ResponseBody
	public List<Map<String,Object>> loadDetail(@PathVariable Integer banUserSeq) {
		return banService.loadDetail(banUserSeq);
	}
	
	@PostMapping(value = "/admin/api/ban/user/{banUserSeq}/days/{duration}")
	@ResponseBody
	public List<Map<String,Object>> banDuration(@PathVariable Integer banUserSeq, @PathVariable Integer duration) {
		return banService.banDuration(banUserSeq,duration);
	}
}
