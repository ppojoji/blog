package naver.ppojoji.blog.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naver.ppojoji.blog.dao.StatDao;
import naver.ppojoji.blog.dto.CountStat;

@Service
public class StatService {
	
	@Autowired
	StatDao statDao;
	
	/**
	 * 일별 가입한 사용자 수 통계 반환
	 * yyyy-mm-dd 23
	 * yyyy-mm-dd 34
	 * yyyy-mm-dd 09
	 * 
	 */
	public List<CountStat> statDayJoinUser() {
		List<CountStat> list = statDao.statDayJoinUser();
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
}
