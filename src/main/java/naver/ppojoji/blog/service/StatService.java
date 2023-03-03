package naver.ppojoji.blog.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naver.ppojoji.blog.Util;
import naver.ppojoji.blog.dao.StatDao;
import naver.ppojoji.blog.dto.CountCommunityStat;
import naver.ppojoji.blog.dto.CountStat;
import naver.ppojoji.blog.dto.PostStat;

@Service
public class StatService {
	
	@Autowired
	StatDao statDao;
	
	/**
	 * 일별 가입한 사용자 수 통계 반환
	 * yyyy-mm-dd 23
	 * yyyy-mm-dd 34
	 * yyyy-mm-dd 09
	 * @param month 
	 * @param year 
	 * 
	 */
	public List<CountStat> statDayJoinUser(String year, String month) {
		LocalDate [] range = Util.getDateRange(year, month); // 
		List<CountStat> list = statDao.statDayJoinUser(range[0], range[1]);
		return list;
	}

	public List<CountStat> statWeekJoinUser() {
		List<CountStat> list = statDao.statWeekJoinUser();
		return list;
	}

	public List<CountStat> statMonthJoinUser() {
		List<CountStat> list = statDao.statMonthJoinUser();
		return list;
	}
	/**
	 * 통계 기록
	 * @param string
	 * @param joindate
	 * @param seq
	 */
	public void insertUserStat(String mode, Date joinDate, Integer userSeq) {
		statDao.insertUserStat(mode, joinDate, userSeq);
	}
	
	public List<CountCommunityStat> statDayCommunity(String year, String month) {
		List<CountCommunityStat> list = statDao.statDayCommunity(year,month);
		return list;
	}

	public List<CountCommunityStat> statWeekCommunity(String year, String month) {
		List<CountCommunityStat> list = statDao.statWeekCommunity(year,month);
		return list;
	}

	public List<CountCommunityStat> statMonthCommunity(String year) {
		List<CountCommunityStat> list = statDao.statMonthCommunity(year);
		return list;
	}

	public void insertPostStat(PostStat stat) {
		statDao.insertPostStat(stat);
		
	}

	public List<CountCommunityStat> statYearCommunity() {
		List<CountCommunityStat> list = statDao.statYearCommunity();
		return list;
	}

	public List<CountCommunityStat> statHourCommunity() {
		List<CountCommunityStat> list = statDao.statHourCommunity();
		/*
		Map<String, String> hourMap = new HashMap<>();
		for(int h = 0; h < 24; h++) {
			String s = String.format("%02d", h); // 13
			String e = String.format("%02d", h + 1);
			hourMap.put("" + h, String.format("%s", s + " ~ " + e + "시"));
		}
		
		for (CountCommunityStat stat : list) {
			String range = stat.getRange();
			// "0" ~ "23"
			stat.setRange(hourMap.get(range));
		}
		*/
		
		return list;
	}
}
