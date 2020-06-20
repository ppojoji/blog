package naver.ppojoji.blog.service.mail;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailingService {

	@Autowired
	private JavaMailSender mailSender;
	private String senderEmail = "ppojoji@gmail.com";
	
	public String SendMail(String receiverEmail , String title , String content) {
		MimeMessage msg = mailSender.createMimeMessage();
		
		MimeMessageHelper messageHelper = new MimeMessageHelper(msg, "UTF-8");

		try {
			
			messageHelper.setFrom(senderEmail);
			messageHelper.setTo(receiverEmail); 
			messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
			messageHelper.setText(content, true);  // 메일 내용
			
			mailSender.send(msg);
			System.out.println("메일 보냈음 " + receiverEmail);
 		} catch (MessagingException e) {
			e.printStackTrace();
		}  
		return null;
	}
	
	
}
