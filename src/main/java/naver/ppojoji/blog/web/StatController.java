package naver.ppojoji.blog.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import naver.ppojoji.blog.dto.CountCommunityStat;
import naver.ppojoji.blog.dto.CountStat;
import naver.ppojoji.blog.service.StatService;
/**
 * 
 * 1. 일별 사용자 가입 숫자 - /admin/stat/user/day
 * 2. 주별 사용자 가입 숫자 - /admin/stat/user/week
 * 3. 월별 사용자 가입 숫자 - /admin/stat/user/month
 * 
 * @author ppojo
 *
 */
@Controller
public class StatController {

	@Autowired
	StatService statService;
	
	@GetMapping(value = "/admin/api/stat/user/day")
	@ResponseBody
	public Object statDayJoinUser() {
		/*
		 * 2021-09-11 34
		 * 2021-09-13 12
		 */
		List<CountStat> list = statService.statDayJoinUser();
		return list;
	}
	
	@GetMapping(value = "/admin/api/stat/user/week")
	@ResponseBody
	public Object statWeekJoinUser() {
		/*
		 * 2021-09-11 34
		 * 2021-09-13 12
		 */
		List<CountStat> list = statService.statWeekJoinUser();
		return list;
	}
	
	@GetMapping(value = "/admin/api/stat/user/month")
	@ResponseBody
	public Object statMonthJoinUser() {
		/*
		 * 2021-09-11 34
		 * 2021-09-13 12
		 */
		List<CountStat> list = statService.statMonthJoinUser();
		return list;
	}
	
	@GetMapping(value = "/admin/api/stat/community/day")
	@ResponseBody
	public Object statDayCommunity() {
		/*
		 * 2021-09-11 34
		 * 2021-09-13 12
		 */
		List<CountCommunityStat> list = statService.statDayCommunity();
		return list;
	}
	
	@GetMapping(value = "/admin/api/stat/community/week")
	@ResponseBody
	public Object statWeekCommunity() {
		/*
		 * 2021-09-11 34
		 * 2021-09-13 12
		 */
		List<CountCommunityStat> list = statService.statWeekCommunity();
		return list;
	}
	
	@GetMapping(value = "/admin/api/stat/community/month")
	@ResponseBody
	public Object statMonthCommunity() {
		/*
		 * 2021-09-11 34
		 * 2021-09-13 12
		 */
		List<CountCommunityStat> list = statService.statMonthCommunity();
		return list;
	}
	
	@GetMapping(value = "/admin/api/stat/community/year")
	@ResponseBody
	public Object statYearCommunity() {
		/*
		 * 2021-09-11 34
		 * 2021-09-13 12
		 */
		List<CountCommunityStat> list = statService.statYearCommunity();
		return list;
	}
	
	@GetMapping(value = "/admin/api/stat/community/hour")
	@ResponseBody
	public Object statHourCommunity() {
		/*
		 * 2021-09-11 34
		 * 2021-09-13 12
		 */
		List<CountCommunityStat> list = statService.statHourCommunity();
		return list;
	}
}
