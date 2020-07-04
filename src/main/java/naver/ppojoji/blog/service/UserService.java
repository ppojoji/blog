package naver.ppojoji.blog.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naver.ppojoji.blog.dao.UserDao;
import naver.ppojoji.blog.dto.User;

@Service
public class UserService {

	@Autowired
	UserDao userDao;
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
	public void userDelete(String id , String email) {
		userDao.userDelete(id,email);
	}
}
