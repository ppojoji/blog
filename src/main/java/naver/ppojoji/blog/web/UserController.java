package naver.ppojoji.blog.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import naver.ppojoji.blog.dto.User;
import naver.ppojoji.blog.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	/* FIXME 이것도 나중에 없앨겁니다. 스프링프레임워크 내부에서 다 해주고 있음 */
	private ObjectMapper om = new ObjectMapper();
	
	
	@RequestMapping(value="/login", method= RequestMethod.GET)
	public String login() {
		return "login";
	}
	@RequestMapping(
			value="/api/login", 
			method = RequestMethod.POST , 
			produces = Value.APPLICATION_JSON_CHARSET_UTF_8 )
	@ResponseBody
	public String login(@RequestParam String id ,@RequestParam String pwd, 
			HttpServletRequest req,
			HttpSession session) throws JsonProcessingException {
		User loginUser = userService.login(id, pwd);
		
		Map<String, Object> res = new HashMap<>();
		if (loginUser == null) {
			// id pwd 뭐가 틀림 로그인 실패
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
			res.put("success", true);
			res.put("nextUrl",nextUrl);
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
		res.put("success", user != null);
		res.put("user", user);
		user.setPwd(null);
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
}
