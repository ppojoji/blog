package naver.ppojoji.blog.dto;

import java.util.Date;

public class OauthToken {
	private String accessToken;
	private String refreshToken;
	private Date expired; 
	private Integer onwer;
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public Date getExpired() {
		return expired;
	}
	public void setExpired(Date expired) {
		this.expired = expired;
	}
	public Integer getOnwer() {
		return onwer;
	}
	public void setOnwer(Integer owner) {
		this.onwer = owner;
	}
	@Override
	public String toString() {
		return "OauthToken [accessToken=" + accessToken + ", refreshToken=" + refreshToken + ", expired=" + expired
				+ ", onwer=" + onwer + "]";
	}
}
