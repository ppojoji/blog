package naver.ppojoji.blog.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import naver.ppojoji.blog.Util;
import naver.ppojoji.blog.dto.OauthToken;
import naver.ppojoji.blog.dto.User;
import naver.ppojoji.blog.service.OauthTokenService;

@Controller
public class OauthTokenController {
	@Autowired
	OauthTokenService oauthTokenService;
	
	@RequestMapping(value="/oauth/imgur/getToken")
	@ResponseBody
	public Object getToken(HttpSession session) {
		User user = Util.getUser(session);
		
		OauthToken token = oauthTokenService.getToken(user.getSeq());
		
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
