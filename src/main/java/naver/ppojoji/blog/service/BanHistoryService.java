package naver.ppojoji.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naver.ppojoji.blog.dao.BanHistoryDao;
import naver.ppojoji.blog.dto.BanHistory;
import naver.ppojoji.blog.dto.User;

@Service
public class BanHistoryService {

	@Autowired
	BanHistoryDao banhistoryDao;

	public void Banhistory(User adminUser,Integer postSeq, String reason) {
		
		
		BanHistory dto =  new BanHistory();
		dto.setAdmin(adminUser.getEmail());
		dto.setPost(postSeq);
		dto.setReason(reason);
		
		banhistoryDao.Banhistory(dto);
	}

	public List<BanHistory> GetBanHistoryByPost(Integer postSeq) {
		List<BanHistory> ban = banhistoryDao.GetBanHistoryByPost(postSeq);
		
		return ban; 
	}
}
