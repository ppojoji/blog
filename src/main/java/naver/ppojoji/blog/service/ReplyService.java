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
		return replyDao.selectReply(seq);
	}

}
