package naver.ppojoji.blog;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;

import naver.ppojoji.blog.dto.User;
import naver.ppojoji.blog.service.MessageService;
import naver.ppojoji.blog.web.Value;

public class Util {

	public static User getUser(HttpSession session) {
		return (User)session.getAttribute(Value.KEY_LOGIN_USER);
	}
	public static String getSession(HttpServletRequest req, String key) {
		String v = (String) req.getSession().getAttribute(key);
		return v;
	}
	
	public static String getSession(HttpServletRequest req, String key, String defaultValue) {
		String v = (String) req.getSession().getAttribute(key);
		if (v == null) {
			return (String)defaultValue;
		} else {			
			return v;
		}
	}
	
	public static Date getDate(HttpServletRequest req, String key) {
		return (Date) req.getSession().getAttribute(key);
	}
	/**
	 * 메일 템플릿 읽어들임
	 * @param templateName
	 * @return
	 */
	public static String readMailTemplate(String templateName) {
		InputStream in = MessageService.class.getResourceAsStream("/mail/template/" + templateName + ".html");
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			IOUtils.copy(in, bos);
			return new String(bos.toByteArray(), "UTF-8");
		} catch (IOException e) {
			throw new BlogException(500, "FAIL_TO_READ_MAILTEMPLATE");
		}
	}
	
	public static Map<String, Object> success(String key, Object value) {
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("success", true);
		res.put(key, value);
		return res;
	}
	public static Map<String, Object> success(String key1, Object value1, String k2, Object v2) {
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("success", true);
		res.put(key1, value1);
		res.put(k2, v2);
		return res;
	}
	public static Map<String, Object> fail(String key, Object value) {
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("success", false);
		res.put(key, value);
		return res;
	}
	public static Cookie findCookie(HttpServletRequest req, String key) {
		Cookie [] cookies = req.getCookies(); // new Cookie[0]
		if (cookies == null ) {
			return null;
		}
		
		for (int i = 0; i < cookies.length; i++) {
			if(key.equals(cookies[i].getName())) {
				return cookies[i];
			}
		}
		return null;
	}
	/**
	 * Util.params("a", 13, "b", 32, "c", "dkdkd")
	 * @param args
	 * @return
	 */
	public static Map<String, Object> params(Object ... args) {
		Map<String, Object> param = new HashMap<String, Object>();
		for (int i = 0; i < args.length; i += 2) {
			String key = (String) args[i];   // 4
			Object val = args[i+1]; // 5
			param.put(key, val);
		}
		return param;
	}
	
	public static void setSession(
			HttpServletRequest req, 
			String key,
			Object value) {
		req.getSession().setAttribute(key, value);
		
	}
	/**
	 * 
	 * @param date
	 * @param millis
	 * @return
	 */
	public static Date dateAfter(Date date, int millis) {
		long after30min = date.getTime() + millis;
		return new Date(after30min);
	}
	
	public static void notEmpty(String value, String cause) {
		if (value == null || value.trim().equals("")) {
			throw new BlogException(400, cause);
		}
	}
}
