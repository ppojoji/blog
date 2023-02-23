package naver.ppojoji.blog.dto;

import java.util.Date;

public class PostStat {
//	public enum StatType {
//		C,
//		D
//	}
//	public StatType mode;
//	public enum AmPm {
//		AM,
//		PM
//	}
	public String mode; // "C", "D"
	public Integer stat_year; // "2023."
	public Integer stat_month; // 1~`2
	public Integer stat_week; // 129
	public Date stat_ymd; // 37737
	public String stat_ampm;
	public Integer stat_hour;
	public Integer ref;
	
	
	
	public Integer getStat_hour() {
		return stat_hour;
	}
	public void setStat_hour(Integer stat_hour) {
		this.stat_hour = stat_hour;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public Integer getStat_year() {
		return stat_year;
	}
	public void setStat_year(Integer stat_year) {
		this.stat_year = stat_year;
	}
	
	public Integer getStat_month() {
		return stat_month;
	}
	public void setStat_month(Integer stat_month) {
		this.stat_month = stat_month;
	}
	public Integer getStat_week() {
		return stat_week;
	}
	public void setStat_week(Integer stat_week) {
		this.stat_week = stat_week;
	}
	public Date getStat_ymd() {
		return stat_ymd;
	}
	public void setStat_ymd(Date stat_ymd) {
		this.stat_ymd = stat_ymd;
	}
	public String getStat_ampm() {
		return stat_ampm;
	}
	public void setStat_ampm(String stat_ampm) {
		this.stat_ampm = stat_ampm;
	}
	public Integer getRef() {
		return ref;
	}
	public void setRef(Integer ref) {
		this.ref = ref;
	}
}
