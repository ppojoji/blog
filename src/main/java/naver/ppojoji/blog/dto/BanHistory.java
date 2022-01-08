package naver.ppojoji.blog.dto;

import java.util.Date;

public class BanHistory {
	private Integer seq;
	private String reason;
	private Date banTime;
	private String admin;
	private Integer post;
	
	public Integer getPost() {
		return post;
	}
	public void setPost(Integer post) {
		this.post = post;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Date getBanTime() {
		return banTime;
	}
	public void setBanTime(Date banTime) {
		this.banTime = banTime;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
}
