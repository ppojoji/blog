package naver.ppojoji.blog.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naver.ppojoji.blog.dao.UserDao;
import naver.ppojoji.blog.dto.User;

@Service
public class UserService {

	@Autowired
	UserDao userDao;
	public User login(String id , String pwd) {
		
		User user = userDao.login(id, pwd);
		System.out.println("로그인? " + user);
		return user;  
	}
	public void userDelete(String id , String email) {
		userDao.userDelete(id,email);
	}
}
