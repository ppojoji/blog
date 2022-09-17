package naver.ppojoji.blog.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import naver.ppojoji.blog.BlogException;
import naver.ppojoji.blog.Error;
import naver.ppojoji.blog.Util;
import naver.ppojoji.blog.dto.User;
import naver.ppojoji.blog.service.BanReporterService;
import naver.ppojoji.blog.service.BookMarkService;
import naver.ppojoji.blog.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	BookMarkService bookMarkService;
	
	 @Autowired
	 BanReporterService banService;
	
	/* FIXME 이것도 나중에 없앨겁니다. 스프링프레임워크 내부에서 다 해주고 있음 */
	private ObjectMapper om = new ObjectMapper();
	
	
	
	
	// @CrossOrigin(origins = "*", allowCredentials = "true")
	@RequestMapping(
			value="/api/login", 
			method = RequestMethod.POST , 
			produces = Value.APPLICATION_JSON_CHARSET_UTF_8 )
	@ResponseBody
	public String login(@RequestParam String id ,@RequestParam String pwd, 
			HttpServletRequest req, HttpServletResponse response,
			HttpSession session, @RequestParam String useCookie) throws JsonProcessingException {
		User loginUser = userService.login(id, pwd,useCookie);
		
		if(loginUser == null) {
			throw new BlogException(410, Error.LOGIN_FAILED);
		}
		
		Map<String, Object> res = new HashMap<>();
		
		if (banService.checkBannedUser(loginUser)) {
			// 얘는 정지기간임
			throw new BlogException(410, Error.BANNED_USER);
		}
		
		String strLoginFailCnt = Util.getSession(req, "LOGIN_FAIL_CNT", "0");
		Integer loginFailCnt = Integer.parseInt(strLoginFailCnt);
		// step : 5번 틀렸는지 확인
		Date banTime = Util.getDate(req, "LOGIN_BAN_TIME");
		if(banTime != null) {
			Date now = new Date();
			if (now.after(banTime)) {
				// 1) banTime < now
				System.out.println("banTime < now");
				loginFailCnt = 0;
				Util.setSession(req, "LOGIN_FAIL_CNT", null);
				Util.setSession(req, "LOGIN_BAN_TIME", null);
			} else {
				// 2) now < banTime - 아직 30분 안지남
				System.out.println("now < banTime");
				/*
				 * WebUtil.ajax(...)
				 * return null;
				 */
				return "{\"success\": false, \"cause\": 30}";
			}
		}
		
		if (loginUser == null) {
			// id pwd 뭐가 틀림 로그인 실패
//			loginFailCnt += 1;
			System.out.println("[로그인 실패 횟수] " + (loginFailCnt+1));
			Util.setSession(req,  "LOGIN_FAIL_CNT", (loginFailCnt+1)+"");
			if (loginFailCnt.intValue() >= 4) {
				System.out.println("[5회 틀렸음] 브라우저에 alert");
				Util.setSession(req, "LOGIN_BAN_TIME", Util.dateAfter(new Date(), 1*60*1000));
			}
			res.put("success", false);
		} else {
			// 로그인 성공 // session에 담아줌!
			String nextUrl = (String) session.getAttribute(Value.KEY_NEXT_URL);
			if(nextUrl == null)
			{
				nextUrl = req.getContextPath();
			}
			// TODO 세션에는 진짜 꼭 필요한 정보만 담아야 함 - why (세선 클러스터링이라는게 있습니다)
			session.removeAttribute(Value.KEY_NEXT_URL);
 			session.setAttribute(Value.KEY_LOGIN_USER, loginUser);
 			
 			/* 
 			 * login key 설정
 			 */
 			Cookie autoLoginCookie =new Cookie("loginCookie", loginUser.getAutoLoginKey());
 			autoLoginCookie.setPath("/");
// 			autoLoginCookie.setMaxAge(60*60*24*7);// 단위는 (초)임으로 7일정도로 유효시간을 설정해 준다.
            // 쿠키를 적용해 준다.
            response.addCookie(autoLoginCookie);
			res.put("success", true);
			res.put("nextUrl",nextUrl);
			res.put("user", loginUser);
		}
		
		
		return om.writeValueAsString(res);
	}
	
	@RequestMapping(value="/myPage", method= RequestMethod.GET)
	public String myPage() {
		return "myPage";
	}
	@RequestMapping(
			value="/api/myinfo",
			method= RequestMethod.GET, 
			produces = Value.APPLICATION_JSON_CHARSET_UTF_8)
	@ResponseBody
	public String myInfo(HttpSession session) throws JsonProcessingException {
		User user = (User) session.getAttribute("LOGIN_USER");
		
		Map<String, Object> res = new HashMap<>();
		if(user != null) {
			res.put("success", user != null);
			res.put("user", user);
			user.setPwd(null);
		}else {
			res.put("success", false);
			res.put("user", user);
		}
		return om.writeValueAsString(res);
	}
	
	@RequestMapping(value="/article/api/userDelete", method= RequestMethod.POST, produces =Value.APPLICATION_JSON_CHARSET_UTF_8)
	@ResponseBody
	public String userDelete(@RequestParam String id ,@RequestParam String email) throws JsonProcessingException {
		this.userService.userDelete(id,email);
		Map<String, Object> res = new HashMap<>();
		res.put("success",true);
		return om.writeValueAsString(res); 
	}
	
	@RequestMapping(value="/logout", method= RequestMethod.GET)
	@ResponseBody
	public String logout(HttpSession session) throws JsonProcessingException {
		session.invalidate();
		// FIXME 로그인 한 시간, 로그아웃한 시간을 기록하고 싶습니다. 
		// 사용시간을 지원하려면 테이블을 어떻게 만들어야 할지....
//		this.userService.userDelete(id);
		return "logout"; 
	}
	@RequestMapping(value = "/user/resetpw")
	public Object resetPw(@RequestParam String email) {
		userService.resetPassword(email);
		return Util.params("success", true, "msg", "메일로 보냈음");
	}
	// CRUD : CREATE(INSERT QUERY) + READ(SELECT) , U(UPDATE), D(DELETE)
	@PostMapping("/user/bookmark/{postSeq}")
	@ResponseBody
	public Object addBookMark(@PathVariable Integer postSeq,HttpSession session) {
		User user = Util.getUser(session);
		if(user == null) {
			throw new BlogException(401, "LOGIN_REQUIRED");
		}
		
		bookMarkService.addBookMark(postSeq, user);
		return Util.params("success", true);
	}
	@DeleteMapping("/user/bookmark/{postSeq}")
	@ResponseBody
	public Object removeBookMark(@PathVariable Integer postSeq,HttpSession session) {
		User user = Util.getUser(session);
		if(user == null) {
			throw new BlogException(401, Error.LOGIN_REQUIRED);
		}
		
		bookMarkService.removeBookMark(postSeq, user);
		return Util.params("success", true);
	}
	
	@RequestMapping(value="/user/join" ,method = RequestMethod.POST)
	@ResponseBody
	public Object Join(
			@RequestParam String id ,
			@RequestParam String email ,
			@RequestParam String pwd, 
			@RequestParam String pwhint,
			@RequestParam String pwhintans) {
		System.out.println(id + " , " + email+ " , " +pwd + " , " + pwhint + " , " + pwhintans); 
		User user = userService.join(id,email,pwd,pwhint,pwhintans);
		
//		Map map = new HashMap<String, Object>();
//		map.put("email", email);
//		map.put("Pwd", Pwd);
//		map.put("SUCCESS", true);
		
		return Util.params("success", true);
	}
	
	@RequestMapping(value="/user/join2" ,method = RequestMethod.POST)
	@ResponseBody
	public Object Join2(
			@RequestBody User user) {
		// user = userService.join(user); 
		
		return Util.params("success", true);
	}
	
	@GetMapping("/user/join/checked")
	@ResponseBody
	public Object checkProp(@RequestParam String prop , @RequestParam String value) {
		userService.checkProp(prop,value);
		return Util.params("success", true);
		
	}
	
	@RequestMapping(value="/api/user/hint" ,method = RequestMethod.POST)
	@ResponseBody
	public Object Hint(
			@RequestParam String email , 
			@RequestParam String hint ,
			@RequestParam String hintAns) {
		// user = userService.join(user); 
		System.out.println(email + ", " + hint + ", " + hintAns);
		
		User user = userService.hint(email,hint,hintAns);
		String id = user.getId();
		String pwd = user.getPwd();
		
		return Util.params("success", true, "id", id, "pwd", pwd);
	}
}
