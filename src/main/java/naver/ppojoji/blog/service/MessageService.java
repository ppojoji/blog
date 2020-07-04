package naver.ppojoji.blog.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import naver.ppojoji.blog.Util;
import naver.ppojoji.blog.dao.MessageDao;
import naver.ppojoji.blog.dao.UserDao;
import naver.ppojoji.blog.dto.Message;
import naver.ppojoji.blog.dto.User;
import naver.ppojoji.blog.service.mail.MailingService;

@Service
public class MessageService {
	@Autowired 
	MessageDao messageDao; 
	
	@Autowired
	MailingService mailService;
	
	@Autowired
	UserDao userDao;
	/**
	 * 새로운 쪽지 기록함
	 * @param msg
	 * @return
	 */
	public void sendMessage(Message msg) {
		// 1. db에 쪽지 저장
		messageDao.insert(msg);
		// 2. 족지 수신자 메일을 얻어와야 함
		
		Integer receiverSeq = msg.getReceiver();
		User receiver = userDao.findUser(receiverSeq);
		String receiverEmail = receiver.getEmail();
		
		String title = "새로운 쪽지 도착";
		String content = Util.readMailTemplate("new-note");
		System.out.println("@@ content" +content);
		content = content.replace("{{name}}", receiver.getId())
				.replace("{{content}}", msg.getContent())
				.replace("{{sender}}", msg.getSender());
		
		mailService.SendMail(receiverEmail, title, content);
//// 		InputStream in = MessageService.class.getResourceAsStream("/mail/template/new-note.html");
//		try {
////			List<String> lines = IOUtils.readLines(in, "utf-8");
////			StringBuilder sb = new StringBuilder();
////			for(String line: lines) {
////				sb.append(line);
////			}
////			String content = sb.toString();
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
	}
	public List<Message> findMessages(int userSeq) {
		return messageDao.findMessages(userSeq);
	}

	public void deleteMessage(Integer seq) {
		messageDao.deleteMessage(seq);
	}
	
	@Transactional
	public Message redaMessages(Integer msgSeq) {
		Message msg = messageDao.readMessage(msgSeq);
		if (ObjectUtils.isEmpty(msg.getReadtime())) {
			messageDao.updateMessage(msgSeq);
		}
		return msg;
	}
	public void replyMessage(String title, String content, Integer messageSeq) {
		Message message = messageDao.readMessage(messageSeq);
		String senderEmail = message.getSender();
		
		mailService.SendMail(senderEmail, title, content);
	}

}
