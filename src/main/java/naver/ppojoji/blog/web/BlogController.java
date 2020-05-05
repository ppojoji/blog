package naver.ppojoji.blog.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import naver.ppojoji.blog.dto.Post;
import naver.ppojoji.blog.dto.User;
import naver.ppojoji.blog.service.BlogService;

@Controller
public class BlogController {

	SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "main";
	}
	
	/**
	 * 블로그 글을 반환합니다.
	 * FIXME 전부다 던져주면 난리남(pagenation 기능이 나중에 들어갈겁니다)
	 * 
	 */
	private ObjectMapper om = new ObjectMapper();
	
	@Autowired
	BlogService blogServise;
	
	@RequestMapping(value="/api/posts", method = RequestMethod.GET, produces = Value.APPLICATION_JSON_CHARSET_UTF_8)
	@ResponseBody // string 이거 가지고 jsp 찾지 말고 바로 보내라 내가 다 했음
	public String listPosts() throws JsonProcessingException {
		// ctrl + alt + 아래/위 화살표 : 복사해서 만듬
		List<Post> list = blogServise.findAllPosts("Y");
		Map<String, Object> map = new HashMap<String, Object>();
		/*
		 * TODO 2020-05-01 LIMIT 시간을 디비에서 읽어들임
		 * => 관리자 페이지에서 내가 설정을 바꿀 수 있게 해봄
		 * => 
		 */
		map.put("limit", 6*60*60*1000); // 6시간 밀리세컨드로 ...
		map.put("posts", list);
		return om.writeValueAsString(map);
		// { "seq": 3000, "title": "ㅇㅇㅇㅇ", "content": "ㅇ미암리ㅏㅇ", viewCount: 1}
	}
	@RequestMapping(value="/api/readPosts/{postSeq}", method= RequestMethod.GET, produces =Value.APPLICATION_JSON_CHARSET_UTF_8)
	@ResponseBody
	public String readPosts(@PathVariable int postSeq
			) 
			throws JsonProcessingException {
		Post post = blogServise.readPosts(postSeq, true);
		/*
		 * FIXME 반환하는 방식이 아주 좋지는 않습니다.
		 */
		/*
		 *  { success: true, post: { ....} }
		 *  
		 *  { success: false, cause: NO_SUCH_POST}
		 */
		/* System.out.println("##file"+files); */
		Map<String, Object> res = new HashMap<>();
		res.put("success", post != null);
		if(post != null) {
			res.put("post", post);
		} else {
			res.put("cause", "NO_SUCH_POST");
		}
		res.put("time", 
				df.format(post.getCreationDate()));
		return om.writeValueAsString(res); // jsp 이름이 아님
	}
	
	@RequestMapping(value="/article/pageReadPost/{postSeq}", method= RequestMethod.GET, produces =Value.APPLICATION_JSON_CHARSET_UTF_8)
	public String pageReadPost() {
		return "read"; // WEB-INF/views/read.jsp
	}
	
	@RequestMapping(value="/article/write", method = RequestMethod.GET ,produces = Value.APPLICATION_JSON_CHARSET_UTF_8 )
	public String write(HttpSession session) {
		// FIXME 지금은 무조건 페이지로 넘어가는데, 실제로는 로그인 정보가 있을때만 페이지로 넘어가아 햡니다.
		return "write";
	}
	
	/**
	 * FIXME 새로운 글을 추가합니다. 
	 * @return
	 * @throws JsonProcessingException 
	 */
	@RequestMapping(value="/article/api/write",method=RequestMethod.POST,produces = Value.APPLICATION_JSON_CHARSET_UTF_8)
	@ResponseBody
	public String insertPost(
			HttpSession session,
			@RequestParam String title,
			@RequestParam String contents,
			@RequestParam List<MultipartFile> files) throws JsonProcessingException {
		// FIXME 지금은 무조건 페이지로 넘어가는데, 실제로는 로그인 정보가 있을때만 페이지로 넘어가아 햡니다.
		User loginUser = (User) session.getAttribute(Value.KEY_LOGIN_USER);
		System.out.println(title);
		System.out.println(contents);
		blogServise.insertPost(title, contents, files, loginUser.getSeq());
		Map<String, Object> res = new HashMap<>();
		res.put("success",true);
		
		return om.writeValueAsString(res);
	}
	/**
	 * @return
	 * @throws JsonProcessingException 
	 */
	@RequestMapping(value="/article/api/update/{postSeq}", method= RequestMethod.POST, produces =Value.APPLICATION_JSON_CHARSET_UTF_8)
	@ResponseBody
	public String updatePost(@RequestParam String title , @RequestParam String contents , @RequestParam Integer postSeq) throws JsonProcessingException {
		// FIXME 지금은 무조건 페이지로 넘어가는데, 실제로는 로그인 정보가 있을때만 페이지로 넘어가아 햡니다.
		this.blogServise.updatePost(title, contents, postSeq);
		System.out.println("##postSeq"+postSeq);
		Map<String, Object> res = new HashMap<>();
		res.put("success",true);
		return om.writeValueAsString(res); 
	}
	@RequestMapping(value="/article/update", method = RequestMethod.GET ,produces = Value.APPLICATION_JSON_CHARSET_UTF_8 )
	public String update(@RequestParam Integer pid, Model model) {
		// FIXME 지금은 무조건 페이지로 넘어가는데, 실제로는 로그인 정보가 있을때만 페이지로 넘어가아 햡니다.
		// /article/update?pid=323333
		System.out.println("post id: " + pid);
		Post post = blogServise.readPosts(pid, false);
		model.addAttribute("ppp", post);
		model.addAttribute("update", true);
		return "write";
		
	}
	/*
	 * TODO 원래는 post 나 delete 로 method를 받아야 합니다.
	 * 화면에서는 앵커를 걸어서 넘기면 안됨(<a ...) => js로 글삭제 클릭했을때 jquery로 요청을 보내야 함
	 * 
	 * 브라우저에서는 응답을 받아서 메인페이지로 이동해야함
	 * (location.href = '....')
	 * 
	 */
//	@RequestMapping(value="/article/delete",method =RequestMethod.GET,produces = APPLICATION_JSON_CHARSET_UTF_8)
//	public String deletePost(@RequestParam Integer pid) {
//		System.out.println("## pid" + pid);
//		blogServise.deletePost(pid);
//		return "redirect:/";
//	}
	
	@RequestMapping(value="/article/api/delete", method= RequestMethod.POST, produces =Value.APPLICATION_JSON_CHARSET_UTF_8)
	@ResponseBody
	public String deletePost(@RequestParam Integer pid) throws JsonProcessingException {
		// FIXME 지금은 무조건 페이지로 넘어가는데, 실제로는 로그인 정보가 있을때만 페이지로 넘어가아 햡니다. 
		// FIXME 지금 로그인한 사람이 지우려는 글의 작성자이어야 함
		
		this.blogServise.deletePost(pid);
		Map<String, Object> res = new HashMap<>();
		System.out.println("##res"+res);
		res.put("success",true);
		return om.writeValueAsString(res); 
	}
	/**
	 * FIXME 글을 비공개 처리함
	 * @return
	 */
	public String setAsSecret() {
		return null;
	}
}
