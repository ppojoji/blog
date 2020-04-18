package naver.ppojoji.blog.web.interceptor;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import naver.ppojoji.blog.web.Value;

public class LoginChecker extends HandlerInterceptorAdapter {

	private List<String> urls = Arrays.asList(
			"/artice/write",
			"/artice/api/write"
	);
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
			throws Exception {
		// System.out.println("[INTERCEPTOR] " +);
		/*
		 * uri 가 urls 배열 안에 포함되는지
		 */
		String uri =  req.getRequestURI(); // 얘가 진짜로 가려고 했던 경로
		HttpSession session = req.getSession();
		if(session.getAttribute(Value.KEY_LOGIN_USER) == null) {
			session.setAttribute(Value.KEY_NEXT_URL, uri);
			res.sendRedirect(req.getContextPath() + "/login");
			return false;
		} else {
			
			return true;			
		}
	}

}
