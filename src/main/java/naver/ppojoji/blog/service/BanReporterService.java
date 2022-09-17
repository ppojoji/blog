package naver.ppojoji.blog.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import naver.ppojoji.blog.BanRepoterEnum;
import naver.ppojoji.blog.dao.admin.BanReporterDao;
import naver.ppojoji.blog.dto.BanRepoter;
import naver.ppojoji.blog.dto.User;

@Service
@Transactional
public class BanReporterService {

	@Autowired
	BanReporterDao banDao;
	/**
	 * 댓글 신고 추가
	 * @param user
	 * @param replySeq
	 * @param banCode
	 */
	public void insertBan(User user, Integer replySeq, String banCode) {
		//Map<String, Object> map = new HashMap<>();
		//map.put("user",user);
		//map.put("replySeq", replySeq);
		//map.put("targetType", "R");
		//map.put("banCode", banCode);
		//banDao.insertBan(map);
		System.out.println("#### user " + user);
		BanRepoter br = new BanRepoter();
		br.setRepoter(user.getSeq());
		br.setSeq(replySeq);
		br.setTargetType(BanRepoterEnum.R);
		br.setBanCode(banCode);
		
		banDao.insertBan(br);
	}
	/**
	 * 원글 신고추가
	 * @param user
	 * @param postSeq
	 * @param banCode
	 */
	public void insertPostBan(User user, Integer postSeq, String banCode) {
		BanRepoter br = new BanRepoter();
		br.setRepoter(user.getSeq());
		br.setSeq(postSeq);
		br.setTargetType(BanRepoterEnum.P);
		br.setBanCode(banCode);
		
		banDao.insertPostBan(br);
		
	}
	/**
	 * 신고 내역 조회
	 * @return
	 */
	public Object getBanPostList() {
		List<Map<String, Object>> banList = banDao.banPostList();
		return banList;
	}
	
	public Object getBanReplyList() {
		List<Map<String, Object>> banList = banDao.banReplyList();
		return banList;
	}
	/**
	 * 신고 내역 처리
	 * @param adminUser
	 * @param banSeq
	 * @param approve
	 * @return 
	 */
	/*
	public BanRepoter processBan(User adminUser, Integer banSeq, boolean approve) {
		BanRepoter ban= banDao.findBanSeq(banSeq);
		if (ban == null) {
			// throw ///
		}
		if(approve == true) {
			ban.setBan_result(1);
		}else {
			ban.setBan_result(0);
		}
		
		banDao.processBan(ban);
		return ban;
	}
	*/
	public BanRepoter processBan(User adminUser, Integer banSeq, boolean decision) {
		BanRepoter ban= banDao.findBanSeq(banSeq);
		
		if(decision) {
			ban.setBan_result(1);
		}else {
			ban.setBan_result(0);
		}
		
		banDao.processBan(ban);
		return ban;
	}
	
	public List<User> badUser() {
		return banDao.badUser();
		
	}
	public List<Map<String,Object>> loadDetail(Integer banUserSeq) {
		return banDao.loadDetail(banUserSeq);
		
	}
	public List<Map<String, Object>> banDuration(Integer banUserSeq, Integer durationInDays) {
		
		Date now = new Date();
		long currentMillis = now.getTime(); // 현재시간 ms
		long banMillis =  currentMillis + 1000L * 60 * 60 * 24 * durationInDays;
		
		Date banTime = new Date(banMillis);
		System.out.println("[ban time] " + banTime);
		banDao.banDuration(banUserSeq, banTime);
		return null;
	}
	/**
	 * 정지기간인지 확인함. 
	 * 정지기간에 해당하면 true, 만일 정지기간이 없거나, 정지기간이 지났으면 banTime을 null로 해제함
	 * @param user
	 */
	public boolean checkBannedUser(User user) {
		Date bandTime = user.getBanTime();
		if (bandTime == null) {
			return false;
		}
		
		Date now = new Date(); // 현재 시간
		/*
		 * 현재 시간과 bandTime 비교함
		 */
		if(bandTime.before(now)) { // now ..... bandTime
			// bandTime.after(now) => now.after(badnTie)
			// bandTime ... now
			bandTime = null;
			banDao.clearBanTime(user.getSeq());
			return false;
		} 
		
		
		return true;
	}
}
