package naver.ppojoji.blog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import naver.ppojoji.blog.BlogException;
import naver.ppojoji.blog.dao.BlogDao;
import naver.ppojoji.blog.dao.ReplyDao;
import naver.ppojoji.blog.dto.Reply;
import naver.ppojoji.blog.dto.User;

@Service
@Transactional
public class ReplyService {
	@Autowired
	ReplyDao replyDao;
	@Autowired
	BlogDao blogDao;
		
	public void replyInsert(Reply reply) {
		replyDao.replyInsert(reply);
	}

	public List<Reply> selectReply(Integer seq) {
		List<Reply> replies = replyDao.selectReply(seq);
		for(int i=0; i<replies.size(); i++) {
			if(replies.get(i).isSecret()) {
				replies.get(i).setTitle("비밀 댓글 입니다.");
				replies.get(i).setContent("비밀 댓글 입니다.");
				replies.get(i).setPwd("****");
			}
			replies.get(i).getContent();
			
		}
		return replies;
	}

	public Reply findReply(Integer seq,String pwd) {
		Reply reply = replyDao.findReply(seq);
		if( pwd.equals(reply.getPwd())) {
			return reply;
		} else {
			return null;
		}
	}

	public int replyDelete(User user, Integer replySeq) {
		Reply reply = replyDao.findReply(replySeq);
		if(reply == null) {
			throw new BlogException(404, "NO_REPLY");
		}
		
		System.out.println("####" +reply.getWriter());
		if (user.getSeq().equals(reply.getWriter().getSeq())) {
			return replyDao.replyDelete(replySeq);
		} else {
			throw new BlogException(403, "NOT_YOUR_REPLY");
		}
		
	}


}
