package naver.ppojoji.blog.dto;

import java.util.Date;

public class User {
	private Integer seq; // null;
	private String id;
	private String pwd; 
	private String email;
	private String pwhint;
	private String pwhintans;
	private String admin;
	private Integer read_note;
	private String userpic;

	private Date banTime;

	private Date joindate;
	
	public Date getJoindate() {
		return joindate;
	}
	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}
	
	public String getUserpic() {
		return userpic;
	}
	public void setUserpic(String userpic) {
		this.userpic = userpic;
	}

	/**
	 * 신고 먹은 글(댓글) 갯수
	 */
	private Integer banCnt;
	
	public Integer getRead_note() {
		return read_note;
	}
	public void setRead_note(Integer read_note) {
		this.read_note = read_note;
	}
	
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	
	private String autoLoginKey;
	
	
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwhint() {
		return pwhint;
	}
	public void setPwhint(String pwhint) {
		this.pwhint = pwhint;
	}
	public String getPwhintans() {
		return pwhintans;
	}
	public void setPwhintans(String pwhintans) {
		this.pwhintans = pwhintans;
	}
	public String getAutoLoginKey() {
		return autoLoginKey;
	}
	public void setAutoLoginKey(String autoLoginKey) {
		this.autoLoginKey = autoLoginKey;
	}
	
	public Integer getBanCnt() {
		return banCnt;
	}
	public void setBanCnt(Integer banCnt) {
		this.banCnt = banCnt;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	
	public Date getBanTime() {
		return banTime;
	}
	public void setBanTime(Date bandTime) {
		this.banTime = bandTime;
	}
	/**
	 * 관리자인지 확인함
	 * @return 관리자이면 true 반환
	 */
	public boolean isAdmin() {
		return this.admin.equals("Y");
	}
	/*
	 * @Override public String toString() { return "User [seq=" + seq + ", id=" + id
	 * + ", pwd=" + pwd + ", email=" + email + ", autoLoginKey=" + autoLoginKey +
	 * ", getSeq()=" + getSeq() + ", getId()=" + getId() + ", getPwd()=" + getPwd()
	 * + ", getEmail()=" + getEmail() + ", getAutoLoginKey()=" + getAutoLoginKey() +
	 * ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
	 * + super.toString() + "]"; }
	 */
	@Override
	public String toString() {
		return "User [seq=" + seq + ", id=" + id + ", pwd=" + pwd + ", email=" + email + ", pwhint=" + pwhint
				+ ", pwhintans=" + pwhintans + ", admin=" + admin + ", autoLoginKey=" + autoLoginKey + "]";
	}
}
