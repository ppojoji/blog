package naver.ppojoji.blog.web.interceptor;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import naver.ppojoji.blog.Util;
import naver.ppojoji.blog.dao.UserDao;
import naver.ppojoji.blog.dto.User;
import naver.ppojoji.blog.web.Value;

public class LoginChecker extends HandlerInterceptorAdapter {

	private List<String> urls = Arrays.asList(
			"/artice/write",
			"/artice/api/write",
			"/myPage"
	);
	
	@Autowired UserDao userDao;
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
			throws Exception {
		// System.out.println("[INTERCEPTOR] " +);
		/*
		 * uri 가 urls 배열 안에 포함되는지
		 */
		String uri =  req.getRequestURI(); // 얘가 진짜로 가려고 했던 경로
		HttpSession session = req.getSession();
		User loginUser = (User) session.getAttribute(Value.KEY_LOGIN_USER);
		
		if(loginUser == null) {
			Cookie loginCookie = Util.findCookie(req, "loginCookie");
			if (loginCookie != null) {
				User user = userDao.findUserByLoginKey(loginCookie.getValue());
				if( user != null) {
					session.setAttribute(Value.KEY_LOGIN_USER, user);
					loginUser = user;
				}
			}
		}
		
		if(loginUser == null) {
			session.setAttribute(Value.KEY_NEXT_URL, uri);
			/*
			 * 일단 쿠키를 읽어야 함
			 */
//			Cookie [] cookies = req.getCookies();
			res.sendRedirect(req.getContextPath() + "/login");
			return false;
		} else {
			
			return true;			
		}
	}

}
