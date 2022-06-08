package naver.ppojoji.blog.dto;

import java.util.Date;

import naver.ppojoji.blog.BanRepoterEnum;

public class BanRepoter {
	private Integer seq;
	private Integer repoter;
	private Integer target;
	private String BanCode;
	private Date repoterTime; 
	private Integer ban_result;
	private BanRepoterEnum targetType;
	

	public BanRepoterEnum getTargetType() {
		return targetType;
	}
	public void setTargetType(BanRepoterEnum targetType) {
		this.targetType = targetType;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) { // getReplySeq()
		this.seq = seq;
	}
	public Integer getRepoter() {
		return repoter;
	}
	public void setRepoter(Integer repoter) {
		this.repoter = repoter;
	}
	public Integer getTarget() {
		return target;
	}
	public void setTarget(Integer target) {
		this.target = target;
	}
	public String getBanCode() {
		return BanCode;
	}
	public void setBanCode(String banCode) {
		BanCode = banCode;
	}
	public Date getRepoterTime() {
		return repoterTime;
	}
	public void setRepoterTime(Date repoterTime) {
		this.repoterTime = repoterTime;
	}
	public Integer getBan_result() {
		return ban_result;
	}
	public void setBan_result(Integer ban_result) {
		this.ban_result = ban_result;
	}
}
