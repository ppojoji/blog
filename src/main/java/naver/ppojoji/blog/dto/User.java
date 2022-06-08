package naver.ppojoji.blog.dto;

public class User {
	private Integer seq; // null;
	private String id;
	private String pwd; 
	private String email;
	private String pwhint;
	private String pwhintans;
	private String admin;
	
	/**
	 * 신고 먹은 글(댓글) 갯수
	 */
	private Integer banCnt;
	
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
