package naver.ppojoji.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naver.ppojoji.blog.dao.ReplyDao;
import naver.ppojoji.blog.dto.Reply;

@Service
public class ReplyService {
	@Autowired
	ReplyDao replyDao;
		
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

}
