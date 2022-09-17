package naver.ppojoji.blog.dao.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import naver.ppojoji.blog.dto.BanRepoter;
import naver.ppojoji.blog.dto.User;

@Repository
public class BanReporterDao {

	@Autowired
	SqlSession session;
	
	/**
	 * 신고 추가
	 * @param br
	 */
	public void insertBan(BanRepoter br) {
		 session.insert("BanReporterMapper.insertBan",br);
		
	}
	/**
	 * 원글 신고 내역 반환
	 * @return
	 */
	public List<Map<String, Object>> banPostList() {
		return session.selectList("BanReporterMapper.banPostList");
	}
	/**
	 * 리플 신고 내역 반환
	 * @return
	 */
	public List<Map<String, Object>> banReplyList() {
		return session.selectList("BanReporterMapper.banReplyList");
	}
	/**
	 * 원글 신고 추가
	 * @param br
	 */
	public void insertPostBan(BanRepoter br) {
		session.insert("BanReporterMapper.insertPostBan",br);
	}
	
	public BanRepoter findBanSeq(Integer banSeq) {
		return session.selectOne("BanReporterMapper.findBanSeq",banSeq);
	}
	public void processBan(BanRepoter ban) {
		session.update("BanReporterMapper.processBan",ban);
		
	}
	public List<User> badUser() {
		return session.selectList("BanReporterMapper.badUser");
		
	}
	public List<Map<String,Object>> loadDetail(Integer banUserSeq) {
		return session.selectList("BanReporterMapper.loadDetail",banUserSeq);
		
	}
	/**
	 * 정지 기간 적용
	 * @param banUserSeq 정지시킬 사용자
	 * @param banTime 시점
	 */
	public void banDuration(Integer banUserSeq, Date banTime) {
		Map<String,Object> map = new HashMap<>();
		
		map.put("banUserSeq", banUserSeq);
		map.put("banTime", banTime);
		
		session.update("BanReporterMapper.banDuration",map);
		
	}
	public void clearBanTime(Integer banUserSeq) {
		// session.update("BanReporterMapper.clearBanTime",banUserSeq);
		banDuration(banUserSeq, null);
		
	}
}
