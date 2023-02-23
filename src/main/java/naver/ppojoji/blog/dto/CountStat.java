package naver.ppojoji.blog.dto;
/**
 * 일별, 주별, 월별 갯수를 나타냄(사용자, 글의 갯수 등)
 * @author ppojo
 *
 */
public class CountStat {

	String range; // 2022-01-09, 2023-34, 2021-10
	Integer joinCnt;
	Integer delCnt;
	
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	public Integer getJoinCnt() {
		return joinCnt;
	}
	public void setJoinCnt(Integer joinCnt) {
		this.joinCnt = joinCnt;
	}
	public Integer getDelCnt() {
		return delCnt;
	}
	public void setDelCnt(Integer delCnt) {
		this.delCnt = delCnt;
	}
	
}
