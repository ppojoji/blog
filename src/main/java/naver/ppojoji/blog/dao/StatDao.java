package naver.ppojoji.blog.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import naver.ppojoji.blog.Util;
import naver.ppojoji.blog.dto.CountCommunityStat;
import naver.ppojoji.blog.dto.CountStat;
import naver.ppojoji.blog.dto.PostStat;

@Repository
public class StatDao {

	@Autowired
	SqlSession session;
	
	public List<CountStat> statDayJoinUser() {
		List<CountStat> list = session.selectList("StatMapper.statDayJoinUser");
		return list;
	}

	public List<CountStat> statWeekJoinUser() {
		List<CountStat> list = session.selectList("StatMapper.statWeekJoinUser");
		return list;
	}

	public List<CountStat> statMonthJoinUser() {
		List<CountStat> list = session.selectList("StatMapper.statMonthJoinUser");
		return list;
	}

	public void insertUserStat(String mode, Date joinDate, Integer userSeq) {
	
		Map<String, Object> param = Util.params(
				"mode", mode,
				"joindate", joinDate,
				"user_ref", userSeq);
		
		session.insert("StatMapper.insertUserStat",param);
	}
	
	public List<CountCommunityStat> statDayCommunity() {
		List<CountCommunityStat> list = session.selectList("StatMapper.statDayCommunity");
		return list;
	}

	public List<CountCommunityStat> statWeekCommunity() {
		List<CountCommunityStat> list = session.selectList("StatMapper.statWeekCommunity");
		return list;
	}

	public List<CountCommunityStat> statMonthCommunity() {
		List<CountCommunityStat> list = session.selectList("StatMapper.statMonthCommunity");
		return list;
	}

	public void insertPostStat(PostStat stat) {
		session.insert("StatMapper.insertPostStat",stat);
	}

	public List<CountCommunityStat> statYearCommunity() {
		List<CountCommunityStat> list = session.selectList("StatMapper.statYearCommunity");
		return list;
	}

	public List<CountCommunityStat> statHourCommunity() {
		List<CountCommunityStat> list = session.selectList("StatMapper.statHourCommunity");
		return list;
	}
}
