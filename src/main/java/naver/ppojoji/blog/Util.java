package naver.ppojoji.blog;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;

import naver.ppojoji.blog.dto.User;
import naver.ppojoji.blog.service.MessageService;
import naver.ppojoji.blog.web.Value;

public class Util {

	public static User getUser(HttpSession session) {
		return (User)session.getAttribute(Value.KEY_LOGIN_USER);
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
}
