package naver.ppojoji.blog;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

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
}
