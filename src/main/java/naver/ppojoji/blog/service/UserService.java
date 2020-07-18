package naver.ppojoji.blog.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naver.ppojoji.blog.dao.UserDao;
import naver.ppojoji.blog.dto.User;
import naver.ppojoji.blog.service.oauth.OAuthService;

@Service
public class UserService {

	@Autowired
	UserDao userDao;
	@Autowired
	OAuthService oauthService;
	public User login(String id , String pwd, String useCookie) {
		
		User user = userDao.login(id, pwd);
		/*
		 * 
		 */
		if (user != null && "true".equals(useCookie)) {
			// generate uuid 
			String autoLoginKey = UUID.randomUUID().toString();
			userDao.updateAutoLoginKey(user.getSeq(), autoLoginKey);
			user.setAutoLoginKey(autoLoginKey);
			//user.setAutoLoginKey(autoLoginKey);
			
		}
		System.out.println("로그인? " + user);
		return user;  
	}
	
	public User loginByOAuth(String accessToken) {
		
		String email = oauthService.findUserEmail(accessToken);
		
		if(email != null) {
			return userDao.findUserByEmail(email);
		} else {
			return null;
		}
	}
	public void userDelete(String id , String email) {
		userDao.userDelete(id,email);
	}
}
