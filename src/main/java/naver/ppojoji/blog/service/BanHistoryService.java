package naver.ppojoji.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naver.ppojoji.blog.BlogException;
import naver.ppojoji.blog.Error;
import naver.ppojoji.blog.dao.BanHistoryDao;
import naver.ppojoji.blog.dao.BlogDao;
import naver.ppojoji.blog.dto.BanHistory;
import naver.ppojoji.blog.dto.Post;
import naver.ppojoji.blog.dto.User;

@Service
public class BanHistoryService {

	@Autowired
	BanHistoryDao banhistoryDao;
	@Autowired
	BlogDao blogDao;

	public void Banhistory(User adminUser,Integer postSeq, String reason) {
		if(!adminUser.getAdmin().equals("Y")) {
			throw new BlogException(401,Error.NOT_ADMIN);
		}
		
		BanHistory dto =  new BanHistory();
		dto.setAdmin(adminUser.getEmail());
		dto.setPost(postSeq);
		dto.setReason(reason);
		
		banhistoryDao.Banhistory(dto);
	}

	public List<BanHistory> GetBanHistoryByPost(User adminUser, Integer postSeq) {
		if(!adminUser.getAdmin().equals("Y")) {
			throw new BlogException(401,Error.NOT_ADMIN);
		}
		
//		BanHistory dto =  new BanHistory();
//		dto.setAdmin(adminUser.getEmail());
//		dto.setPost(dto.getPost());
		Post post = blogDao.findPostBySeq(postSeq);
		if(post != null) {
			List<BanHistory> ban = banhistoryDao.GetBanHistoryByPost(post);
			return ban;
		}else {
			throw new BlogException(404, "NOT_FOUND");
		}
	}
	/**
	 * 주이진 글의 가장 마지막 금지 사유를 반환
	 * @param post
	 * @return
	 */
	public BanHistory findRecentBan(Post post) {
		BanHistory ban  =  banhistoryDao.findRecentBan(post);
		return ban;
	}
	
}
