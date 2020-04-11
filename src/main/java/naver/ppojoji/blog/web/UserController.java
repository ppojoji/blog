package naver.ppojoji.blog.web;

import java.util.HashMap;
import java.util.Map;

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
	public String login(@RequestParam String id ,@RequestParam String pwd, HttpSession session) throws JsonProcessingException {
		User loginUser = userService.login(id, pwd);
		
		Map<String, Object> res = new HashMap<>();
		if (loginUser == null) {
			// id pwd 뭐가 틀림 로그인 실패
			res.put("success", false);
		} else {
			// 로그인 성공 // session에 담아줌!
			session.setAttribute("LOGIN_USER", loginUser);
			res.put("success", true);
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
}
