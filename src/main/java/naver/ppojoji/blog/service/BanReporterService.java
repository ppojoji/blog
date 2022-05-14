package naver.ppojoji.blog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import naver.ppojoji.blog.dao.admin.BanReporterDao;
import naver.ppojoji.blog.dto.User;

@Service
@Transactional
public class BanReporterService {

	@Autowired
	BanReporterDao banDao;

	public void insertBan(User user, Integer replySeq, String banCode) {
		Map<String, Object> map = new HashMap<>();
		map.put("user",user);
		map.put("replySeq", replySeq);
		map.put("targetType", "R");
		map.put("banCode", banCode);
		banDao.insertBan(map);
	}
	/**
	 * 신고 내영 조회
	 * @return
	 */
	public Object getBanList() {
		List<Map<String, Object>> banList = banDao.banList();
		Map<String, Object> ban = banList.get(0);
		/*
		Integer resporter = ban.get("reporter");
		
		User reporter = ban.get("reporter");
		*/
		return banList;
	}
}
