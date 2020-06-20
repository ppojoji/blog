package naver.ppojoji.blog.dao;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class TestSendMail {
	public static void main(String[] args) throws Exception { 
			
		String email = "ppojoji@gmail.com"; 
		String pass = "jjun7458"; 
		
		// 1. 메일 서버 접속 정보 
		Properties prop = new Properties(); 
		prop.put("mail.smtp.host", "smtp.gmail.com"); 
		prop.put("mail.smtp.port", "587"); 
		
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true"); 
		
		Session session = Session.getDefaultInstance( 
			prop, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() { 
				// 구글 메일 서버 로그인 정보 
				return new PasswordAuthentication(email, pass); 
					} 
				}); 
		// 2. 메일 작성 
		MimeMessage msg = new MimeMessage(session); 
		
		// 2.1. 보내는 사람 
		InternetAddress sender = new InternetAddress(email, "ADMIN"); 
		msg.setFrom(sender); 
		
		// 2.2. 받는 사람 
		InternetAddress recv = new InternetAddress("ppojoji@naver.com"); 
		msg.setRecipient(Message.RecipientType.TO, recv); 
		
		// 2.3. 제목과 본문 
		msg.setSubject("[MAILING] FROM TEST ACCOUNT"); 
		msg.setContent("<p><b>good morning</b> body</p>", "text/html");
		
		// 3. 전송 
		Transport.send(msg);
	}
}