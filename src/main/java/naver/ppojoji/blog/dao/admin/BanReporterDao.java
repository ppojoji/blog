package naver.ppojoji.blog.dao.admin;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BanReporterDao {

	@Autowired
	SqlSession session;
	
	/**
	 * 신고 추가
	 * @param map
	 */
	public void insertBan(Map map) {
		 session.insert("BanReporterMapper.insertBan",map);
		
	}
	/**
	 * 신고 내영 반환
	 * @return
	 */
	public List<Map<String, Object>> banList() {
		return session.selectList("BanReporterMapper.banList");
	}
}
