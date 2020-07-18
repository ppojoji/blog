package naver.ppojoji.blog.service.mail;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import naver.ppojoji.blog.Util;
import naver.ppojoji.blog.dao.MailingDao;
import naver.ppojoji.blog.dto.Mail;

@Service
public class MailingService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	MailingDao mailingDao;
	
//	private String senderEmail = "ppojoji@gmail.com";
	@Value("${mailing.sender}")
	private String senderEmail;
	
	
	@PostConstruct
	public void checkSenderEmail() {
		
		System.out.println(">>>>>>>> " + senderEmail);
	}
	
//	@Transactional
//	@Scheduled(cron = "50 * * * * *")
//	public void scheduleMailing() {
//		List<Map<String, Object>> jobs = mailingDao.getBeforeMail();
//		for(int i=0;i<jobs.size();i++) {
//			Map<String, Object> job = jobs.get(i);
//			// {SENDRESULT=-1, SENDER=yeori.seo@gmail.com, SUBJECT=새로운 쪽지 도착, CONTENT
//			
//			String seq = (String)job.get("SEQ");
//			String receiver = (String)job.get("RECEIVER");
//			String title = (String) job.get("SUBJECT");
//			String content = (String) job.get("CONTENT");
//			
//			/*
//			String seq = mail.getSeq();
//			String receiver = mail.getReceiver(); 
//			String title = mail.getSubject();
//			String content = mail.getContent();
//			*/
//			boolean result = SendMail(receiver , title , content);
//			//boolean result = SendMail(mail);
//			if(result == true) {
//				// sendresult 값을 0으로 update 해줌
//				mailingDao.updateMailingResult(Long.parseLong(seq), 0);
//			} else {
//				// 1로 update를 해줌
//				mailingDao.updateMailingResult(Long.parseLong(seq), 1);
//			}
//		}
//	}
//	
//	public boolean SendMail(String receiverEmail , String title , String content) {
//		// FIXME 위에 3개 파라미터 말고 MailDto 를 만들어서 넣어야 나중에 편함
//		MimeMessage msg = mailSender.createMimeMessage();
//		
//		MimeMessageHelper messageHelper = new MimeMessageHelper(msg, "UTF-8");
//
//		try {
//			
//			messageHelper.setFrom(senderEmail);
//			messageHelper.setTo(receiverEmail);
//			messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
//			messageHelper.setText(content, true);  // 메일 내용
//			mailSender.send(msg);
//			System.out.println("메일 보냈음 " + receiverEmail);
//			return true;
// 		} catch (MessagingException e) {
//			e.printStackTrace();
//			return false;
//		}  
//	}
	
	@Transactional
	@Scheduled(cron = "50 * * * * *")
	public void scheduleMailing() {
		//Mail mail = (Mail) mailingDao.getBeforeMail();
		List<Mail> jobs = mailingDao.getBeforeMail();
		System.out.println("#### jobs" + jobs.toString());
		for(int i=0;i<jobs.size();i++) {
				// {SENDRESULT=-1, SENDER=yeori.seo@gmail.com, SUBJECT=새로운 쪽지 도착, CONTENT
//			String seq = jobs.get(i).getSeq();
//			String receiver = jobs.get(i).getReceiver(); 
//			String title = jobs.get(i).getSubject();
//			String content = jobs.get(i).getContent();
//			Map<String, Object> param = Util.params("7receiver",receiver,"title",title,"content",content);	
			Mail m = jobs.get(i);
			boolean result = SendMail(m);
			//boolean result = SendMail(mail);
			long seq = Long.parseLong(m.getSeq());
			if(result == true) {
				// sendresult 값을 0으로 update 해줌
				mailingDao.updateMailingResult(seq, 0);
			} else {
				// 1로 update를 해줌
				mailingDao.updateMailingResult(seq, 1);
			}
		}
	}

	
	public boolean SendMail(Mail mail) {
		String receiverEmail = mail.getReceiver(); 
		String title = mail.getSubject();
		String content = mail.getContent();
		
		// FIXME 위에 3개 파라미터 말고 MailDto 를 만들어서 넣어야 나중에 편함
		MimeMessage msg = mailSender.createMimeMessage();
		
		MimeMessageHelper messageHelper = new MimeMessageHelper(msg, "UTF-8");

		try {
			
			messageHelper.setFrom(senderEmail);
			messageHelper.setTo(receiverEmail);
			messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
			messageHelper.setText(content, true);  // 메일 내용
			mailSender.send(msg);
			System.out.println("메일 보냈음 " + receiverEmail);
			return true;
 		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}  
	}
	
	public void registerMail(String sender, String receiver, String subject, String content) {
		mailingDao.registerMail(sender, receiver, subject,content);
	}
	
	public void registerMail(Mail mail) {
		String sender = mail.getSender();
		String receiver = mail.getReceiver();
		String subject = mail.getSubject();
		String content = mail.getContent();
		
		mailingDao.registerMail(sender, receiver, subject,content);
	}
	
}
