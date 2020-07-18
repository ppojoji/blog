package naver.ppojoji.blog.web.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import naver.ppojoji.blog.Util;
import naver.ppojoji.blog.dto.User;
import naver.ppojoji.blog.service.UserService;

@Controller
public class OAthNaverController {
	@Autowired 
	UserService userService;
	@GetMapping("/oauth/naver/callback")
	public String naverCallback() {
		
		return "oauth/login-callback";
	}
	@RequestMapping("/oauth/naverLogin") 
	@ResponseBody
	public Object naverLogin(@RequestParam String accessToken) {
		User user = userService.loginByOAuth(accessToken);
		// FIXME SESSION에 담아줘야 함
		
		return Util.params("success",true, "user", user);
	}
}
