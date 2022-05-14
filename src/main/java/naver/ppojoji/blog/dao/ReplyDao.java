package naver.ppojoji.blog.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import naver.ppojoji.blog.dto.Post;
import naver.ppojoji.blog.dto.Reply;

@Repository
public class ReplyDao {
	@Autowired
	SqlSession session;
	
	public void replyInsert(Reply reply) {
		session.insert("ReplyMapper.replyInsert",reply);
	}
	/**
	 * 원글의 답글들 조회
	 * @param postSeq
	 * @return
	 */
	public List<Reply> selectReply(Integer postSeq) {
		return session.selectList("ReplyMapper.selectReply", postSeq);
	}
	/**
	 * 주어진 PK의 답글 하나
	 * @param replySeq
	 */
	public Reply findReply(Integer replySeq) {
		return session.selectOne("ReplyMapper.selectReplyBySeq", replySeq);
	}
	public int replyDelete(Integer seq) {
		return session.delete("ReplyMapper.replyDelete", seq);
	}
	

}
