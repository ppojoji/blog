package naver.ppojoji.blog.web;

import java.text.SimpleDateFormat;
import java.util.Arrays;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import naver.ppojoji.blog.BanType;
import naver.ppojoji.blog.BlogException;
import naver.ppojoji.blog.Util;
import naver.ppojoji.blog.dto.BanHistory;
import naver.ppojoji.blog.dto.Category;
import naver.ppojoji.blog.dto.MultiSearch;
import naver.ppojoji.blog.dto.Post;
import naver.ppojoji.blog.dto.Reply;
import naver.ppojoji.blog.dto.Search;
import naver.ppojoji.blog.dto.Tag;
import naver.ppojoji.blog.dto.User;
import naver.ppojoji.blog.service.BanHistoryService;
import naver.ppojoji.blog.service.BlogService;
import naver.ppojoji.blog.service.BookMarkService;
import naver.ppojoji.blog.service.CategoryService;
import naver.ppojoji.blog.service.PostDeletion;
import naver.ppojoji.blog.service.ReplyService;
import naver.ppojoji.blog.service.TagService;

@Controller
public class BlogController {

	SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@Autowired PostDeletion del;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
//		System.out.println(">>>>>> :: " + del.deletePost());
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
	
	@Autowired
	ReplyService replyServise;
	
	@Autowired
	CategoryService cateGoryService;
	
	@Autowired
	BanHistoryService banHistoryService;
	
	@Autowired
	BookMarkService bookMarkService;
	
	@RequestMapping(value="/api/posts", method = RequestMethod.GET, produces = Value.APPLICATION_JSON_CHARSET_UTF_8)
	@ResponseBody // string 이거 가지고 jsp 찾지 말고 바로 보내라 내가 다 했음
	public String listPosts(
			@RequestParam(required = false) String searchType ,
			@RequestParam(required = false) String keyword) throws JsonProcessingException {
	//public String listPosts() throws JsonProcessingException {
		// ctrl + alt + 아래/위 화살표 : 복사해서 만듬
		//List<Post> list = blogServise.findAllPosts("Y");
		
		
		List<Post> list = blogServise.findAllPosts(true);
		Map<String, Object> map = new HashMap<String, Object>();
		List<Category> cata = cateGoryService.findAllCate();
		/*
		 * TODO 2020-05-01 LIMIT 시간을 디비에서 읽어들임
		 * => 관리자 페이지에서 내가 설정을 바꿀 수 있게 해봄
		 * => 
		 */
		map.put("limit", 6*60*60*1000); // 6시간 밀리세컨드로 ...
		map.put("posts", list);
		map.put("cata", cata);
		//map.put("search", search);
		return om.writeValueAsString(map);
		// { "seq": 3000, "title": "ㅇㅇㅇㅇ", "content": "ㅇ미암리ㅏㅇ", viewCount: 1}
	}
	@GetMapping("/api/admin/posts")
	@ResponseBody
	public Object findAllByAdmin(HttpSession session,String searchType){
		User adminUser = Util.getUser(session);
		List<Post> posts = blogServise.findAllByAdmin(searchType,adminUser);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("posts", posts);
		map.put("success", true);
		return map;
	}
	
	@RequestMapping(value="/api/posts/cate/{cateName}")
	@ResponseBody
	public Object ListPostsByCata(@PathVariable String cateName) {
//		List<Post> list = blogServise.findByCateName(cateName);
		List<Post> list = blogServise.findByCate2(cateName);
		
		return Util.success("posts", list,"limit", 6*60*60*1000);
	}
	@RequestMapping(value="/api/search", method = RequestMethod.GET , produces =Value.APPLICATION_JSON_CHARSET_UTF_8)
	@ResponseBody
	public String SearchPost(@RequestParam String searchType , @RequestParam String keyword) throws JsonProcessingException {
		
		
		Search search = new Search();

		search.setSearchType(searchType);
		search.setKeyword(keyword);
		
		List<Post> list = blogServise.searchPost(search);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search",list);
		return om.writeValueAsString(map);
	}
	@RequestMapping(value="/api/multiSearch", method = RequestMethod.GET , produces =Value.APPLICATION_JSON_CHARSET_UTF_8)
	@ResponseBody
	public String MultiSearchPost(@RequestParam List<String> multiSearchType , @RequestParam String multiKeyword) throws JsonProcessingException {
		// title, contents, writer
		System.out.println("## multiSearchType" + multiSearchType);
		System.out.println("## multiKeyword" + multiKeyword);
		MultiSearch search = new MultiSearch();

		if(multiSearchType.contains("title") == true) {
			search.setTitle(true);
		}
		
		if(multiSearchType.contains("contents") == true) {
			search.setContents(true);
		}
		
		if(multiSearchType.contains("writer") == true) {
			search.setWriter(true);
		}
		
		search.setMultiKeyword(multiKeyword);
		
		List<Post> list = blogServise.multiSearchPost(search);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("multiSearch",list);
		return om.writeValueAsString(map);
	}
	@RequestMapping(value="/api/post/pass/{postSeq}", method= RequestMethod.POST, produces =Value.APPLICATION_JSON_CHARSET_UTF_8)
	@ResponseBody
	public String readPostWithPass(
			@PathVariable Integer postSeq,
			@RequestParam String pass) throws JsonProcessingException {
		Post post = blogServise.readPosts(postSeq, false);
		Map <String,Object> map = new HashMap<String, Object>(); 
		if(pass.equals(post.getViewPass())) {
			// OK!
			map.put("success", true); 
			map.put("post", post);
		} else {
			// FAIL!
			map.put("success", false); 
			map.put("cause","fail");
		}
		return om.writeValueAsString(map);
	}
	/**
	 *    메소드는 딱 한가지 일만 해야함!
	 *    
	 *  "/api/readPosts/3323
	 * @param postSeq
	 * @param pass
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value="/api/readPosts/{postSeq}", method= RequestMethod.GET, produces =Value.APPLICATION_JSON_CHARSET_UTF_8)
	@ResponseBody
	public String readPosts(
			@PathVariable int postSeq,
			HttpSession session) 
			throws JsonProcessingException {
		
		Post post = blogServise.readPosts(postSeq, true);
		
		User loginUser = Util.getUser(session);
		Integer cnt = bookMarkService.readBookMark(postSeq,loginUser);
		try {
			Thread.sleep(1*100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Category> cata = cateGoryService.findAllCate();
		Map<String, Object> res = new HashMap<>();
		if(post != null) {
			if(post.getViewPass() != null) {
				res.put("success", false);
				res.put("cause", "VIEW_PASS");
			} else {
				res.put("success", true);
				res.put("post", post);
				res.put("cate", cata);
			}
		} else {
			res.put("success", false);
			res.put("cause", "NO_SUCH_POST");
		}
		res.put("bookMark", cnt);
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
			@RequestParam Integer cate,
			@RequestParam List<Integer> tag,
			@RequestParam List<MultipartFile> files) throws JsonProcessingException {
		// FIXME 지금은 무조건 페이지로 넘어가는데, 실제로는 로그인 정보가 있을때만 페이지로 넘어가아 햡니다.
		User loginUser = (User) session.getAttribute(Value.KEY_LOGIN_USER);
		System.out.println("tags: " + tag); // 출력 [34, 53, 1]
		System.out.println(title);
		System.out.println(contents);
		blogServise.insertPost(title, contents, files, cate, loginUser.getSeq(),tag);
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
	public String updatePost(
			@RequestParam String title ,
			@RequestParam String contents , 
			@RequestParam Integer cateSeq,
			@RequestParam List<Integer> tag, // tag 의 seq 만 배열로 모아서 입력받음
			@PathVariable Integer postSeq,
			HttpSession session
			) throws JsonProcessingException {
		// FIXME 지금은 무조건 페이지로 넘어가는데, 실제로는 로그인 정보가 있을때만 페이지로 넘어가아 햡니다.
		// FIXME 로그인 확인 안함. 그리고 자기 글인지도 확인 안함.
		System.out.println("##postSeq"+postSeq);
		System.out.println("tags" + tag);
		User loginUser = (User) session.getAttribute(Value.KEY_LOGIN_USER);
		this.blogServise.updatePost(title, contents, cateSeq,tag, postSeq,loginUser);
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
	public String deletePost(@RequestParam Integer pid, HttpSession session) throws JsonProcessingException {
		// FIXME 지금은 무조건 페이지로 넘어가는데, 실제로는 로그인 정보가 있을때만 페이지로 넘어가아 햡니다. 
		// FIXME 지금 로그인한 사람이 지우려는 글의 작성자이어야 함
		
		// FIXME 아래와 같이 진짜로 지우면 안되고, 지운다는 표시만 하는 메소드들을 만들어야 함
		
		User loginUser = (User) session.getAttribute(Value.KEY_LOGIN_USER);
		this.blogServise.setAsdeleted(loginUser.getSeq(), pid);
		// this.blogServise.deletePost(pid);
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
	@RequestMapping(value = "/api/mypost",method = RequestMethod.GET, produces =Value.APPLICATION_JSON_CHARSET_UTF_8)
	@ResponseBody
	public String userPost(HttpSession session) throws JsonProcessingException {
		User loginUser =(User)session.getAttribute(Value.KEY_LOGIN_USER);
		int writerSeq = loginUser.getSeq();
		List<Post> writerPost = blogServise.findPostsByWriter(writerSeq); // writeSeq-1-integer
		Map<String, Object> res = new HashMap<>();
		res.put("writerPost", writerPost);
		res.put("success", true);
		return om.writeValueAsString(res);
	}
	@RequestMapping(value = "/api/toggle", method = RequestMethod.POST, produces = Value.APPLICATION_JSON_CHARSET_UTF_8)
	@ResponseBody
	public String togglePost(HttpSession session,@RequestParam Integer seq) throws JsonProcessingException {
		User loginUser =(User)session.getAttribute(Value.KEY_LOGIN_USER);
		Post post = blogServise.togglePost(seq,loginUser);

		Map<String, Object> res = new HashMap<>();
		res.put("success", true);
		res.put("open",post.getOpen());
		return om.writeValueAsString(res);
	}
	@RequestMapping(value="/article/reply" ,method = RequestMethod.GET)
	public String reply(HttpServletRequest req, HttpSession session, @RequestParam Integer pid) {
		Post post = blogServise.readPosts(pid, false);
		// model.setAttribute("", post);
		req.setAttribute("post", post);
		return "reply";
	}
	@RequestMapping(value = "/article/replyPost",method = RequestMethod.POST)
	public String replyPost(
			@RequestParam Integer parent,
			@RequestParam String title,
			@RequestParam String contents,
			@RequestParam String writer,
			@RequestParam String pwd
			){
		Reply reply = new Reply(null, title, contents, writer, pwd, parent);
		replyServise.replyInsert(reply);
		/*
		 * http://localhost:8080/blog/article/pageReadPost/5013
		 */
		return "redirect:/article/pageReadPost/" + parent;		
	}
	@RequestMapping(value = "article/replies/{seq}" , method = RequestMethod.GET)
	@ResponseBody
	public List<Reply> selectReply(@PathVariable Integer seq) {
		List<Reply> reply = replyServise.selectReply(seq);
		return reply;
	}
	@RequestMapping(value = "/api/checkPass", method = RequestMethod.POST, produces = Value.APPLICATION_JSON_CHARSET_UTF_8)
	@ResponseBody
	public String CheckPass(@RequestParam Integer seq , @RequestParam String pwd) throws JsonProcessingException {
		Reply reply = replyServise.findReply(seq, pwd); 
		
		Map<String, Object> res = new HashMap<>();
		res.put("success", reply != null);
		res.put("reply", reply);
		return om.writeValueAsString(res);
	}
	
//	@RequestMapping(value="/cate/{cateName}", method= RequestMethod.GET, produces =Value.APPLICATION_JSON_CHARSET_UTF_8)
//	public String cate() {
//		//List<Post> list = blogServise.findAllPosts(true);
//		return "cate"; 
//	}
	@RequestMapping(value="/cate/{name}", method= RequestMethod.GET, produces =Value.APPLICATION_JSON_CHARSET_UTF_8)
	@ResponseBody
	public List<Category> cate(@PathVariable String name) {
		List<Category> list = cateGoryService.sameNameCate(name);
		return list;
	}
	
	@GetMapping("/api/post/overviews")
	@ResponseBody
	public Object MaxNByCate() {
		List<Map<String,Object>> maxNData = blogServise.findRecentNForCates(3);
		Map<String, Object> res = new HashMap<>();
		res.put("success", true);
		res.put("overviews", maxNData);
		return res;
	}
	
	@PutMapping("/api/post/{pid}")
	@ResponseBody
	public Object UpdatePost(@PathVariable Integer pid , @RequestBody Map<String, Object> param) {
		String prop = (String) param.get("prop");
		// class java.lang.Integer cannot be cast to class java.lang.String
		Object value = param.get("value"); // "abc"
		System.out.println("prop:"+prop + ", value:" + value);
		
		blogServise.updatePost(null, pid,prop,value);
		Map<String, Object> res = new HashMap<>();
		res.put("success", true);
		return res;
	}
	/**
	 * [관리자용] 모든 글 조회
	 * @param session
	 * @return
	 */
	@GetMapping("/admin/api/posts/{searchType}")
	@ResponseBody
	public Object listAllPosts(HttpSession session , @PathVariable String searchType) {
		User adminUser = Util.getUser(session);
		List<Post> posts = null;
		
		System.out.println("@@@@ "+ searchType); // @@@@ AD,PN,AB
		/*
		 * "AD,PN,AB" => ["AD", "PN", "AB"]
		 * 
		 * String [] arr = { 'A', ..}
		 */
		List<String> banTypes = Arrays.asList( searchType.split(","));
		// banTypes = ["all"]
		
//		if("all".equals(searchType)) {
//			posts = blogServise.findAllByAdmin(adminUser);
//		} else if ("AD".equals(searchType)){
//			posts = blogServise.findBanByAdmin(searchType,adminUser);
//		} else if ("PN".equals(searchType)){
//			posts = blogServise.findBanByAdmin(searchType,adminUser);
//		} else if ("AB".equals(searchType)){
//			posts = blogServise.findBanByAdmin(searchType,adminUser);
//		} else if ("GM".equals(searchType)){
//			posts = blogServise.findBanByAdmin(searchType,adminUser);
//		} else if ("ET".equals(searchType)){
//			posts = blogServise.findBanByAdmin(searchType,adminUser);
//		} else {
//			throw new BlogException(400, "NO_SUCH_SEARCHTYPE");
//		}
		/* FIXME 이제 배열임
		if("all".equals(banTypes[0])) { // AD,PN
			searchType = null;
		} else if ("AD".equals(searchType)){
		} else if ("PN".equals(searchType)){
		} else if ("AB".equals(searchType)){
		} else if ("GM".equals(searchType)){
		} else if ("ET".equals(searchType)){
		} else {
			throw new BlogException(400, "NO_SUCH_SEARCHTYPE");
		}
		*/
		if ("all".equals(banTypes.get(0))) {
			banTypes = null;
		} else {
			for (String ban : banTypes) {
				if ("AD".equals(ban)){
				} else if ("PN".equals(ban)){
				} else if ("AB".equals(ban)){
				} else if ("GM".equals(ban)){
				} else if ("ET".equals(ban)){
				} else {
					throw new BlogException(400, "NO_SUCH_SEARCHTYPE");
				}
			}
		}
		// BanType banType = BanType.toBanType(searchType);
		
		posts = blogServise.findAllByAdmin(banTypes,adminUser);
		Map<String, Object> res = new HashMap<>();
		res.put("success", true);
		res.put("posts", posts);
		
		return res;
	}
	
//	@GetMapping("/admin/api/posts/del")
//	@ResponseBody
//	public Object postsDeleted(HttpSession session) {
//		// FIXME /admin으로 시작하는 요청은 전부 권한을 확인해야함
//		// FIXME "/admin"으로 시작하는 uri는 슈퍼 관리자만 실행해야함
//		User adminUser = Util.getUser(session);
//		
//		List<Post> posts = blogServise.findPostsByWriter(adminUser);
//		Map<String, Object> res = new HashMap<>();
//		res.put("success", true);
//		res.put("posts", posts);
//		return res;
//	}
	/**
	 * 관리자가 실제로 글을 지움
	 * @param pid
	 * @return
	 */
	@DeleteMapping("/admin/api/delete/{pid}")
	@ResponseBody
	public Object PostDelete(HttpSession session, @PathVariable Integer pid) {
		User adminUser = Util.getUser(session);
		
		blogServise.postDelete(adminUser,pid);
		
		Map<String, Object> res = new HashMap<>();
		res.put("success", true);
		res.put("pid", pid);
		return res;
	}
	/*
	 * @PutMapping("/admin/api/post/{postSeq}/policy/{code}")
	 * 
	 * @ResponseBody public Object PostPolicy(@PathVariable Integer postSeq
	 * , @PathVariable String code) { // FIXME /admin으로 시작하는 요청은 전부 권한을 확인해야함
	 * blogServise.setPostPolicy(postSeq,code);
	 * 
	 * Map<String, Object> res = new HashMap<>(); res.put("ban", code);
	 * res.put("success", true); return res; }
	 */
	
	@PostMapping("/admin/api/post/{postSeq}/policy/{code}")
	@ResponseBody
	public Object Banhistory(HttpSession session,
			@PathVariable Integer postSeq ,
			@PathVariable String code) {
		User adminUser = Util.getUser(session);
		
		blogServise.Banhistory(adminUser, postSeq, code); // "null"
		long banTime = new Date().getTime();
		Map<String, Object> res = new HashMap<>();
		res.put("ban", "null".equals(code) ? null : code);
		res.put("banTime", banTime);
		res.put("success", true);
		return res;
	}
	
	@GetMapping("/admin/api/post/{postSeq}/ban")
	@ResponseBody
	public Object GetBanHistoryByPost(HttpSession session, @PathVariable Integer postSeq) {
		User adminUser = Util.getUser(session);
		List<BanHistory> ban = banHistoryService.GetBanHistoryByPost(adminUser,postSeq);
		
		Map<String, Object> res = new HashMap<>();
		res.put("ban", ban);
		res.put("success", true);
		return res;
	}
}
