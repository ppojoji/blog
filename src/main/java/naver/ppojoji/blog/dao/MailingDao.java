package naver.ppojoji.blog.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import naver.ppojoji.blog.Util;
import naver.ppojoji.blog.dto.Mail;

@Repository
public class MailingDao {
	@Autowired
	private SqlSession session;
	/**
	 * 메일을 등록함
	 */
	
	public void registerMail(String sender, String receiver, String subject, String content) {
//		Map<String, V>
		Map<String, Object> param = Util.params(
				"sender", sender, 
				"receiver", receiver, 
				"subject", subject, 
				"content", content);
		session.insert("MailingMapper.registerMail", param);	
	}
	
	public List<Map<String, Object>> getBeforeMail() {
		/*
		 * 이렇게 map오로 만들면 일단 편함
		 * but, 나중에 코드 읽기 되게 더러움
		 * 
		 */
		return session.selectList("MailingMapper.getBeforeMail");
		
	}
/*	
	public List<Mail> getBeforeMail() {
		return session.selectList("MailingMapper.getBeforeMail");
		
	}
*/	
	public void updateMailingResult(Long jobSeq, int resCode) {
		Map<String, Object> param = Util.params("jobSeq" ,jobSeq , "resCode" , resCode);
		session.update("MailingMapper.updateMailingResult",param);
		
	}
}
