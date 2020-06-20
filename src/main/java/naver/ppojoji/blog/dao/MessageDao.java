package naver.ppojoji.blog.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import naver.ppojoji.blog.dto.Message;

@Repository
public class MessageDao {

	@Autowired 
	SqlSession session;
	
	public void insert(Message message) {
		session.insert("MessageMapper.messageInsert",message);
		
	}

	public List<Message> findMessages(int userSeq) {
		return	session.selectList("MessageMapper.findMessages", userSeq);
	}

	public void deleteMessage(Integer seq) {
		session.delete("MessageMapper.deleteMessage",seq);
	}

	public Message readMessage(Integer msgSeq) {
		return session.selectOne("MessageMapper.readMessage", msgSeq);
	}

	public void updateMessage(Integer msgSeq) {
		session.update("MessageMapper.updateMessage", msgSeq);
	}

}
