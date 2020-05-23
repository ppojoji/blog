package naver.ppojoji.blog.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import naver.ppojoji.blog.dto.Reply;

@Repository
public class ReplyDao {
	@Autowired
	SqlSession session;
	
	public void replyInsert(Reply reply) {
		session.insert("ReplyMapper.replyInsert",reply);
	}

	public List<Reply> selectReply(Integer seq) {
		return session.selectList("ReplyMapper.selectReply", seq);
	}

}
