package naver.ppojoji.blog.dao;

import java.time.LocalDate;
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
	
	public List<CountStat> statDayJoinUser(LocalDate start, LocalDate end) {
		Map map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		
		List<CountStat> list = session.selectList("StatMapper.statDayJoinUser",map);
		return list;
	}

	public List<CountStat> statWeekJoinUser(LocalDate start, LocalDate end) {
		Map map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		
		List<CountStat> list = session.selectList("StatMapper.statWeekJoinUser",map);
		return list;
	}

	public List<CountStat> statMonthJoinUser(LocalDate start, LocalDate end) {
		Map map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		
		List<CountStat> list = session.selectList("StatMapper.statMonthJoinUser",map);
		return list;
	}

	public void insertUserStat(String mode, Date joinDate, Integer userSeq) {
	
		Map<String, Object> param = Util.params(
				"mode", mode,
				"joindate", joinDate,
				"user_ref", userSeq);
		
		session.insert("StatMapper.insertUserStat",param);
	}
	
	public List<CountCommunityStat> statDayCommunity(String year, String month) {
	//public List<CountCommunityStat> statDayCommunity(LocalDate start, LocalDate end) {
		Map map = new HashMap<>();
		map.put("year",year);
		map.put("month",month);
		//map.put("start",start);
		//map.put("end",end);
		
		List<CountCommunityStat> list = session.selectList("StatMapper.statDayCommunity",map);
		return list;
	}

	public List<CountCommunityStat> statWeekCommunity(String year) {
		Map map = new HashMap<>();
		map.put("year",year);
		
		List<CountCommunityStat> list = session.selectList("StatMapper.statWeekCommunity",map);
		return list;
	}

	public List<CountCommunityStat> statMonthCommunity(String year) {
		List<CountCommunityStat> list = session.selectList("StatMapper.statMonthCommunity",year);
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
