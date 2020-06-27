package naver.ppojoji.blog.web.oauth;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import naver.ppojoji.blog.Util;
import naver.ppojoji.blog.dto.User;
import naver.ppojoji.blog.service.OauthTokenService;

@Controller
public class OAuthImgurController {
	@Autowired
	OauthTokenService oauthTokenService;
	
	// http://localhost:8080/blog/oauth/imgur/callback
	@GetMapping("/oauth/imgur/callback")
	public String onCallbackPage(Model model, HttpServletRequest req) {
		
		model.addAttribute("responseServerName", "IMGUR");
		return "oauth/result";
	}
	/* session.setAttribute("imgur.tokens", ...)
	 * yeori: tokens,
	 * ccccc: tokens,
	 */
	@PostMapping(value = "/oauth/imgur/saveToken")
	@ResponseBody
	public Object saveToken(@RequestBody Map<String, String> tokens, HttpSession session) {
		/*
		 * TODO 일단은 사용자의 토큰을 세선에 담아서 애플리케이션을 구헌합니다.
		 * 
		 * 
		 */
		User user = Util.getUser(session);
		// oauthTokenService.getToken(user.getSeq());
		oauthTokenService.saveToken(user.getSeq(), tokens);
		session.setAttribute("imgur.token", tokens);
		System.out.println("TOKENS: " + tokens.toString());
		return tokens;
	}
	@GetMapping(value = "/oauth/imgur/loadTokens")
	@ResponseBody
	public Object loadTokens(HttpSession session) {
		
		Map<String,String> token = (Map<String, String>) session.getAttribute("imgur.token");
		
		Map<String, Object> map = new HashMap<>();
		if(token != null) {
			map.put("success", true);
			map.put("token", token);
		}else {
			map.put("success", false);
		}
		return map;
	}
}
