package naver.ppojoji.blog.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class TestSpringMail {
	
	@Autowired
	JavaMailSender mailSender;
	@Test
	public void test() throws MessagingException {
		MimeMessage msg = mailSender.createMimeMessage();
		
		MimeMessageHelper messageHelper 
        = new MimeMessageHelper(msg, "UTF-8");

		messageHelper.setFrom("ppojoji@gmail.com");  // 보내는사람 생략하거나 하면 정상작동을 안함
		messageHelper.setTo("ppojoji@naver.com");     // 받는사람 이메일
		
		// 메일 템플릿이 필요합니다.
		messageHelper.setSubject("test"); // 메일제목은 생략이 가능하다
		messageHelper.setText("<b>html로 문서를</b> <font color=\"red\">작성</read>함", true);  // 메일 내용
		
		mailSender.send(msg);
		
	}

}
