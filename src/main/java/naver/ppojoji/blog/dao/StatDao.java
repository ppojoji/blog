package naver.ppojoji.blog.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import naver.ppojoji.blog.Util;
import naver.ppojoji.blog.dto.CountStat;

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
}
