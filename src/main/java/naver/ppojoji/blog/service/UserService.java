package naver.ppojoji.blog.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naver.ppojoji.blog.BlogException;
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

	public User join(String id, String email, String pwd, String pwhint, String pwhintans) {
		// 올바른 email 형식 ??
		// 비번이 없는 경우 막아야 함??
		// email 중복 여부 확인???
		// 1. null이 아니어야 함 - id, email, pwd
		// 2. 길이 확인해야함 - pwd, id
		
		
		User user = userDao.findUserById(id);
		
		if(user != null) {
			throw new BlogException(400, "DUP_ID");
		}
		if (pwd.length() < 6) {
			throw new BlogException(400, "PW_TO_SHORT");
		}
		if (pwd.length() > 12) {
			throw new BlogException(400, "PW_TO_LONG");
		}
		System.out.println(id + " , " + email+ " , " +pwd + " , " + pwhint + " , " + pwhintans); 
		userDao.join(id,email,pwd,pwhint,pwhintans);
		return user;
	}

	public void checkProp(String prop, String value) {
		if ("id".equals(prop)) {
			User user = userDao.findUserById(value);			
			if(user != null) {
				throw new BlogException(400, "DUP_ID");
			}
		}
		else if("email".equals(prop)) {
			User user = userDao.findUserByEmail(value);
			if(user != null) {
				throw new BlogException(400, "DUP_EMAIL");
			}
		}
		else {
			throw new BlogException(400, "NO_SUCH_PROP");
		}
		
	}

}
