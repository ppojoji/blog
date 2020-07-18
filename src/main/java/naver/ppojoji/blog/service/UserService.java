package naver.ppojoji.blog.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naver.ppojoji.blog.dao.UserDao;
import naver.ppojoji.blog.dto.Mail;
import naver.ppojoji.blog.dto.User;
import naver.ppojoji.blog.service.mail.MailingService;
import naver.ppojoji.blog.service.oauth.OAuthService;

@Service
public class UserService {

	@Autowired
	UserDao userDao;
	@Autowired
	OAuthService oauthService;
	@Autowired
	MailingService mailingService;
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
	
	public void resetPassword(String email) {
		/*
		 * 1) 베일로 비번을 쏴줌(임시비번)
		 * 
		 * 2) 비번 재설정 url을 전송함
		 * 
		 */
		User user = userDao.findUserByEmail(email);
		String pwd = UUID.randomUUID().toString();
		pwd = pwd.substring(0, 20);
		
		user.setPwd(pwd);
		
		userDao.UpdatePwd(user.getEmail(), user.getPwd());
		
		String title = "비번 변경됨";
		String content = "임시 비번을 설정했습니다. [" + pwd + "]";
		String receiver = user.getEmail();
		Mail mail = new Mail(title, content, receiver);
		mailingService.SendMail(mail);
		
		
	}
}
